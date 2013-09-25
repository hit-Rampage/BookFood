/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryan.manage.entity;

/**
 *
 * @author ryan
 */
public class UserEntity {
    private int userId;
    private String userName;
    private String password;
    private String userState;

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the userState
     */
    public String getUserState() {
        return userState;
    }

    /**
     * @param userState the userState to set
     */
    public void setUserState(String userState) {
        this.userState = userState;
    }
    
}
