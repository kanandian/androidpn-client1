package org.androidpn.utils;

import org.jivesoftware.smack.XMPPConnection;

import java.lang.reflect.Field;

/**
 * Created by pro1 on 18/3/2.
 */

public class UserInfoHolder {
    private static UserInfoHolder userInfoHolder = null;

    private String userId;
    private String userName;
    private String password;

    private String name;
    private String mobile;

    private boolean isAuth;

    private boolean isRealUser;
    private String imageURL;

    private UserInfoHolder() {
        this.isAuth = false;
    }

    public static UserInfoHolder getInstance() {
        if (userInfoHolder == null) {
            synchronized (UserInfoHolder.class) {
                if (userInfoHolder == null) {
                    userInfoHolder = new UserInfoHolder();
                }
            }
        }

        return userInfoHolder;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isAuth() {
        return isAuth;
    }

    public void setAuth(boolean auth) {
        isAuth = auth;
    }

    public boolean isRealUser() {
        return isRealUser;
    }

    public void setRealUser(boolean realUser) {
        isRealUser = realUser;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setUserToConnection(String userName) {
        XMPPConnection connection = ActivityHolder.getInstance().getConnection();
        String user = connection.getUser();

        String[] userContents = user.split("[@]");
        String newUser = userName+"@"+userContents[1];

        Class<?> clazz = connection.getClass();
        try {
            Field userField = clazz.getDeclaredField("user");
            userField.setAccessible(true);
            userField.set(connection, newUser);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
