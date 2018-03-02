package org.androidpn.utils;

/**
 * Created by pro1 on 18/3/2.
 */

public class UserInfoHolder {
    private static UserInfoHolder userInfoHolder = null;

    private String userName;
    private String password;

    private String name;
    private String mobile;

    private boolean isAuth;

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
}
