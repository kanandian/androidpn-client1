/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.androidpn.client;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

import org.androidpn.IQ.ActivityInquiryIQ;
import org.androidpn.IQ.BussinessIQ;
import org.androidpn.IQ.CommentsIQ;
import org.androidpn.IQ.FoodMenuIQ;
import org.androidpn.IQ.ImageURLIQ;
import org.androidpn.IQ.LoginResponseIQ;
import org.androidpn.IQ.NotificationIQ;
import org.androidpn.IQ.OrderDetailIQ;
import org.androidpn.IQ.PaymentResponseIQ;
import org.androidpn.IQ.RegistrationResponseIQ;
import org.androidpn.IQ.ResultModelIQ;
import org.androidpn.IQ.TakeoutListIQ;
import org.androidpn.IQprovider.ActivityIQProvider;
import org.androidpn.IQprovider.AdminResponseIQProvider;
import org.androidpn.IQprovider.BussinessIQProvider;
import org.androidpn.IQprovider.CommentsIQProvider;
import org.androidpn.IQprovider.FoodMenuIQProvider;
import org.androidpn.IQprovider.ImageURLIQProvider;
import org.androidpn.IQprovider.LoginIQProvider;
import org.androidpn.IQprovider.NotificationIQProvider;
import org.androidpn.IQprovider.OrderDetailIQProvider;
import org.androidpn.IQprovider.PaymentResponseIQProvider;
import org.androidpn.IQprovider.RegistrationIQProvider;
import org.androidpn.IQprovider.TakeoutListIQProvider;
import org.androidpn.packetlistener.ActivityPacketListener;
import org.androidpn.packetlistener.BussinessPacketListener;
import org.androidpn.packetlistener.CommentsPacketListener;
import org.androidpn.packetlistener.FoodMenuPacketListener;
import org.androidpn.packetlistener.ImageURLPacketListener;
import org.androidpn.packetlistener.LoginReponsePacketListener;
import org.androidpn.packetlistener.NotificationPacketListener;
import org.androidpn.packetlistener.OrderDetailPacketListener;
import org.androidpn.packetlistener.PaymentResponsePacketListener;
import org.androidpn.packetlistener.RegistrationResponsePacketListener;
import org.androidpn.packetlistener.ResultModelPacketListener;
import org.androidpn.packetlistener.TakeoutListPacketListener;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.UserInfoHolder;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Registration;
import org.jivesoftware.smack.provider.ProviderManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.util.Log;

/**
 * This class is to manage the XMPP connection between client and server.
 * 
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class XmppManager {

    private static final String LOGTAG = LogUtil.makeLogTag(XmppManager.class);

    private static final String XMPP_RESOURCE_NAME = "AndroidpnClient";

    private Context context;

    private NotificationService.TaskSubmitter taskSubmitter;

    private NotificationService.TaskTracker taskTracker;

    private SharedPreferences sharedPrefs;

    private String xmppHost;

    private int xmppPort;

    private XMPPConnection connection;

    private String username;

    private String password;

    private ConnectionListener connectionListener;

    private PacketListener notificationPacketListener;

    private PacketListener activityPacketListener;

    private PacketListener bussinessPacketListener;

    private PacketListener registrationResponsePacketListener;

    private PacketListener loginResponsePacketListener;

    private PacketListener commentPacketListener;

    private PacketListener foodMenuPacketListener;

    private PacketListener paymentResponsePacketListener;

    private PacketListener takeoutListPacketListener;

    private PacketListener orderDetailPacketListener;

    private PacketListener resultModelPacketListener;

    private PacketListener imageURLPacketListener;

    private Handler handler;

    private List<Runnable> taskList;

    private boolean running = false;

    private Future<?> futureTask;

    private Thread reconnection;

    public XmppManager(NotificationService notificationService) {
        context = notificationService;
        taskSubmitter = notificationService.getTaskSubmitter();
        taskTracker = notificationService.getTaskTracker();
        sharedPrefs = notificationService.getSharedPreferences();

        xmppHost = sharedPrefs.getString(Constants.XMPP_HOST, "localhost");
        xmppPort = sharedPrefs.getInt(Constants.XMPP_PORT, 5222);
        username = sharedPrefs.getString(Constants.XMPP_USERNAME, "");
        password = sharedPrefs.getString(Constants.XMPP_PASSWORD, "");

        connectionListener = new PersistentConnectionListener(this);
        notificationPacketListener = new NotificationPacketListener(this);
        activityPacketListener = new ActivityPacketListener(this);
        bussinessPacketListener = new BussinessPacketListener(this);
        registrationResponsePacketListener = new RegistrationResponsePacketListener(this);
        loginResponsePacketListener = new LoginReponsePacketListener(this);
        commentPacketListener = new CommentsPacketListener(this);
        foodMenuPacketListener = new FoodMenuPacketListener(this);
        paymentResponsePacketListener = new PaymentResponsePacketListener(this);
        takeoutListPacketListener = new TakeoutListPacketListener(this);
        orderDetailPacketListener = new OrderDetailPacketListener(this);
        resultModelPacketListener = new ResultModelPacketListener(this);
        imageURLPacketListener = new ImageURLPacketListener();

        handler = new Handler();
        taskList = new ArrayList<Runnable>();
        reconnection = new ReconnectionThread(this);
    }

    public Context getContext() {
        return context;
    }

    public void connect() {
        Log.d(LOGTAG, "connect()...");
        submitLoginTask();
    }

    public void disconnect() {
        Log.d(LOGTAG, "disconnect()...");
        terminatePersistentConnection();
    }

    public void terminatePersistentConnection() {
        Log.d(LOGTAG, "terminatePersistentConnection()...");
        Runnable runnable = new Runnable() {

            final XmppManager xmppManager = XmppManager.this;

            public void run() {
                if (xmppManager.isConnected()) {
                    Log.d(LOGTAG, "terminatePersistentConnection()... run()");
                    xmppManager.getConnection().removePacketListener(
                            xmppManager.getNotificationPacketListener());
                    xmppManager.getConnection().disconnect();
                }
                xmppManager.runTask();
            }

        };
        addTask(runnable);
    }

    public XMPPConnection getConnection() {
        return connection;
    }

    public void setConnection(XMPPConnection connection) {
        this.connection = connection;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ConnectionListener getConnectionListener() {
        return connectionListener;
    }

    public PacketListener getNotificationPacketListener() {
        return notificationPacketListener;
    }

    public PacketListener getActivityPacketListener() {
        return activityPacketListener;
    }

    public PacketListener getBussinessPacketListener() {
        return bussinessPacketListener;
    }

    public PacketListener getRegistrationResponsePacketListener() {
        return registrationResponsePacketListener;
    }

    public PacketListener getLoginResponsePacketListener() {
        return loginResponsePacketListener;
    }

    public PacketListener getCommentPacketListener() {
        return commentPacketListener;
    }

    public PacketListener getFoodMenuPacketListener() {
        return foodMenuPacketListener;
    }

    public PacketListener getPaymentResponsePacketListener() {
        return paymentResponsePacketListener;
    }

    public PacketListener getTakeoutListPacketListener() {
        return takeoutListPacketListener;
    }

    public PacketListener getOrderDetailPacketListener() {
        return orderDetailPacketListener;
    }

    public PacketListener getResultModelPacketListener() {
        return resultModelPacketListener;
    }

    public PacketListener getImageURLPacketListener() {
        return imageURLPacketListener;
    }

    public void startReconnectionThread() {
        synchronized (reconnection) {
            if (!reconnection.isAlive()) {
                reconnection.setName("Xmpp Reconnection Thread");
                reconnection.start();
            }
        }
    }

    public Handler getHandler() {
        return handler;
    }

    public void reregisterAccount() {
        removeAccount();
        submitLoginTask();
        runTask();
    }

    public List<Runnable> getTaskList() {
        return taskList;
    }

    public Future<?> getFutureTask() {
        return futureTask;
    }

    public void runTask() {
        Log.d(LOGTAG, "runTask()...");
        synchronized (taskList) {
            running = false;
            futureTask = null;
            if (!taskList.isEmpty()) {
                Runnable runnable = (Runnable) taskList.get(0);
                taskList.remove(0);
                running = true;
                futureTask = taskSubmitter.submit(runnable);
                if (futureTask == null) {
                    taskTracker.decrease();
                }
            }
        }
        taskTracker.decrease();
        Log.d(LOGTAG, "runTask()...done");
    }

    private String newRandomUUID() {
        String uuidRaw = UUID.randomUUID().toString();
        return uuidRaw.replaceAll("-", "");
    }

    private boolean isConnected() {
        return connection != null && connection.isConnected();
    }

    private boolean isAuthenticated() {
        return connection != null && connection.isConnected()
                && connection.isAuthenticated();
    }

    private boolean isRegistered() {
        return sharedPrefs.contains(Constants.XMPP_USERNAME)
                && sharedPrefs.contains(Constants.XMPP_PASSWORD);
    }

    private void submitConnectTask() {
        Log.d(LOGTAG, "submitConnectTask()...");
        addTask(new ConnectTask());
    }

    private void submitRegisterTask() {
        Log.d(LOGTAG, "submitRegisterTask()...");
        submitConnectTask();
        addTask(new RegisterTask());
    }

    private void submitLoginTask() {
        Log.d(LOGTAG, "submitLoginTask()...");
        submitRegisterTask();
        addTask(new LoginTask());
    }

    private void addTask(Runnable runnable) {
        Log.d(LOGTAG, "addTask(runnable)...");
        taskTracker.increase();
        synchronized (taskList) {
            if (taskList.isEmpty() && !running) {
                running = true;
                futureTask = taskSubmitter.submit(runnable);
                if (futureTask == null) {
                    taskTracker.decrease();
                }
            } else {
                taskList.add(runnable);
            }
        }
        Log.d(LOGTAG, "addTask(runnable)... done");
    }

    private void removeAccount() {
        Editor editor = sharedPrefs.edit();
        editor.remove(Constants.XMPP_USERNAME);
        editor.remove(Constants.XMPP_PASSWORD);
        editor.commit();
    }

    /**
     * A runnable task to connect the server. 
     */
    private class ConnectTask implements Runnable {

        final XmppManager xmppManager;

        private ConnectTask() {
            this.xmppManager = XmppManager.this;
        }

        public void run() {
            Log.i(LOGTAG, "ConnectTask.run()...");

            if (!xmppManager.isConnected()) {
                // Create the configuration for this new connection
                ConnectionConfiguration connConfig = new ConnectionConfiguration(
                        xmppHost, xmppPort);
                // connConfig.setSecurityMode(SecurityMode.disabled);
                connConfig.setSecurityMode(SecurityMode.required);
                connConfig.setSASLAuthenticationEnabled(false);
                connConfig.setCompressionEnabled(false);

                XMPPConnection connection = new XMPPConnection(connConfig);
                xmppManager.setConnection(connection);

                try {
                    // Connect to the server
                    connection.connect();
                    Log.i(LOGTAG, "XMPP connected successfully");

                    // packet provider
                    ProviderManager.getInstance().addIQProvider("notification",
                            "androidpn:iq:notification",
                            new NotificationIQProvider());
                    ProviderManager.getInstance().addIQProvider("activity",
                            "androidpn:iq:inquiry",
                            new ActivityIQProvider());
                    ProviderManager.getInstance().addIQProvider("bussiness",
                            "androidpn:iq:inquiry",
                            new BussinessIQProvider());
                    ProviderManager.getInstance().addIQProvider("registeration",
                            "androidpn:iq:registeration",
                            new RegistrationIQProvider());
                    ProviderManager.getInstance().addIQProvider("login",
                            "androidpn:iq:login",
                            new LoginIQProvider());
                    ProviderManager.getInstance().addIQProvider("comment",
                            "androidpn:iq:comment",
                            new CommentsIQProvider());
                    ProviderManager.getInstance().addIQProvider("foodmeniu",
                            "androidpn:iq:inquiry",
                            new FoodMenuIQProvider());
                    ProviderManager.getInstance().addIQProvider("payment",
                            "androidpn:iq:payment",
                            new PaymentResponseIQProvider());
                    ProviderManager.getInstance().addIQProvider("order",
                            "androidpn:order:takeout",
                            new TakeoutListIQProvider());
                    ProviderManager.getInstance().addIQProvider("order",
                            "androidpn:iq:orderdetail",
                            new OrderDetailIQProvider());
                    ProviderManager.getInstance().addIQProvider("admin",
                            "androidpn:admin:operation",
                            new AdminResponseIQProvider());
                    ProviderManager.getInstance().addIQProvider("image",
                            "androidpn:iq:inquiry",
                            new ImageURLIQProvider());

                } catch (XMPPException e) {
                    Log.e(LOGTAG, "XMPP connection failed", e);
                }

                xmppManager.runTask();

            } else {
                Log.i(LOGTAG, "XMPP connected already");
                xmppManager.runTask();
            }
        }
    }

    /**
     * A runnable task to register a new user onto the server. 
     */
    private class RegisterTask implements Runnable {

        final XmppManager xmppManager;

        private RegisterTask() {
            xmppManager = XmppManager.this;
        }

        public void run() {
            Log.i(LOGTAG, "RegisterTask.run()...");

            if (!xmppManager.isRegistered()) {
                final String newUsername = newRandomUUID();
                final String newPassword = newRandomUUID();

                Registration registration = new Registration();

                PacketFilter packetFilter = new AndFilter(new PacketIDFilter(
                        registration.getPacketID()), new PacketTypeFilter(
                        IQ.class));

                PacketListener packetListener = new PacketListener() {

                    public void processPacket(Packet packet) {
                        Log.d("PacketListener",
                                "processPacket().....");
                        Log.d("PacketListener", "packet="
                                + packet.toXML());

                        if (packet instanceof IQ) {
                            IQ response = (IQ) packet;
                            if (response.getType() == IQ.Type.ERROR) {
                                if (!response.getError().toString().contains(
                                        "409")) {
                                    Log.e(LOGTAG,
                                            "Unknown error while registering XMPP account! "
                                                    + response.getError()
                                                            .getCondition());
                                }
                            } else if (response.getType() == IQ.Type.RESULT) {
                                xmppManager.setUsername(newUsername);
                                xmppManager.setPassword(newPassword);
                                Log.d(LOGTAG, "username=" + newUsername);
                                Log.d(LOGTAG, "password=" + newPassword);

                                Editor editor = sharedPrefs.edit();
                                editor.putString(Constants.XMPP_USERNAME,
                                        newUsername);
                                editor.putString(Constants.XMPP_PASSWORD,
                                        newPassword);
                                editor.commit();
                                Log
                                        .i(LOGTAG,
                                                "Account registered successfully");
                                xmppManager.runTask();
                            }
                        }
                    }
                };

                connection.addPacketListener(packetListener, packetFilter);

                registration.setType(IQ.Type.SET);
                // registration.setTo(xmppHost);
                // Map<String, String> attributes = new HashMap<String, String>();
                // attributes.put("username", rUsername);
                // attributes.put("password", rPassword);
                // registration.setAttributes(attributes);
                registration.addAttribute("username", newUsername);
                registration.addAttribute("password", newPassword);
                connection.sendPacket(registration);

            } else {
                Log.i(LOGTAG, "Account registered already");
                xmppManager.runTask();
            }
        }
    }

    /**
     * A runnable task to log into the server. 
     */
    private class LoginTask implements Runnable {

        final XmppManager xmppManager;

        private LoginTask() {
            this.xmppManager = XmppManager.this;
        }

        public void run() {
            Log.i(LOGTAG, "LoginTask.run()...");

            if (!xmppManager.isAuthenticated()) {
                Log.d(LOGTAG, "username=" + username);
                Log.d(LOGTAG, "password=" + password);

                try {
                    xmppManager.getConnection().login(
                            xmppManager.getUsername(),
                            xmppManager.getPassword(), XMPP_RESOURCE_NAME);
                    Log.d(LOGTAG, "Loggedn in successfully");

                    UserInfoHolder.getInstance().setUserName(xmppManager.getUsername());
                    UserInfoHolder.getInstance().setPassword(xmppManager.getPassword());

                    // connection listener
                    if (xmppManager.getConnectionListener() != null) {
                        xmppManager.getConnection().addConnectionListener(
                                xmppManager.getConnectionListener());
                    }

                    // packet filter
                    PacketFilter packetFilter = new PacketTypeFilter(
                            NotificationIQ.class);
                    // packet listener
                    PacketListener packetListener = xmppManager
                            .getNotificationPacketListener();
                    connection.addPacketListener(packetListener, packetFilter);

                    PacketFilter packetFilter1 = new PacketTypeFilter(ActivityInquiryIQ.class);
                    PacketListener packetListener1 = xmppManager.getActivityPacketListener();
                    connection.addPacketListener(packetListener1, packetFilter1);

                    PacketFilter packetFilter2 = new PacketTypeFilter(BussinessIQ.class);
                    PacketListener packetListener2 = xmppManager.getBussinessPacketListener();
                    connection.addPacketListener(packetListener2, packetFilter2);

                    PacketFilter packetFilter3 = new PacketTypeFilter(RegistrationResponseIQ.class);
                    PacketListener packetListener3 = xmppManager.getRegistrationResponsePacketListener();
                    connection.addPacketListener(packetListener3, packetFilter3);

                    PacketFilter packetFilter4 = new PacketTypeFilter(LoginResponseIQ.class);
                    PacketListener packetListener4 = xmppManager.getLoginResponsePacketListener();
                    connection.addPacketListener(packetListener4, packetFilter4);

                    PacketFilter packetFilter5 = new PacketTypeFilter(CommentsIQ.class);
                    PacketListener packetListener5 = xmppManager.getCommentPacketListener();
                    connection.addPacketListener(packetListener5, packetFilter5);

                    PacketFilter packetFilter6 = new PacketTypeFilter(FoodMenuIQ.class);
                    PacketListener packetListener6 = xmppManager.getFoodMenuPacketListener();
                    connection.addPacketListener(packetListener6, packetFilter6);

                    PacketFilter packetFilter7 = new PacketTypeFilter(PaymentResponseIQ.class);
                    PacketListener packetListener7 = xmppManager.getPaymentResponsePacketListener();
                    connection.addPacketListener(packetListener7, packetFilter7);

                    PacketFilter packetFilter8 = new PacketTypeFilter(TakeoutListIQ.class);
                    PacketListener packetListener8 = xmppManager.getTakeoutListPacketListener();
                    connection.addPacketListener(packetListener8, packetFilter8);

                    PacketFilter packetFilter9 = new PacketTypeFilter(OrderDetailIQ.class);
                    PacketListener packetListener9 = xmppManager.getOrderDetailPacketListener();
                    connection.addPacketListener(packetListener9, packetFilter9);

                    PacketFilter packetFilter10 = new PacketTypeFilter(ResultModelIQ.class);
                    PacketListener packetListener10 = xmppManager.getResultModelPacketListener();
                    connection.addPacketListener(packetListener10, packetFilter10);

                    PacketFilter packetFilter11 = new PacketTypeFilter(ImageURLIQ.class);
                    PacketListener packetListener11 = xmppManager.getImageURLPacketListener();
                    connection.addPacketListener(packetListener11, packetFilter11);

                    xmppManager.runTask();

                    ActivityHolder.getInstance().setConnection(connection);
                    ActivityHolder.getInstance().setXmppManager(xmppManager);

                } catch (XMPPException e) {
                    Log.e(LOGTAG, "LoginTask.run()... xmpp error");
                    Log.e(LOGTAG, "Failed to login to xmpp server. Caused by: "
                            + e.getMessage());
                    String INVALID_CREDENTIALS_ERROR_CODE = "401";
                    String errorMessage = e.getMessage();
                    if (errorMessage != null
                            && errorMessage
                                    .contains(INVALID_CREDENTIALS_ERROR_CODE)) {
                        xmppManager.reregisterAccount();
                        return;
                    }
                    xmppManager.startReconnectionThread();

                } catch (Exception e) {
                    Log.e(LOGTAG, "LoginTask.run()... other error");
                    Log.e(LOGTAG, "Failed to login to xmpp server. Caused by: "
                            + e.getMessage());
                    xmppManager.startReconnectionThread();
                }

            } else {
                Log.i(LOGTAG, "Logged in already");
                xmppManager.runTask();
            }

        }
    }

}
