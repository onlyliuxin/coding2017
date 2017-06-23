package com.coderising.ood.srp;

/**
 * the user info entity class.
 *
 * @author Thomson Tang
 * @version Created: 23/06/2017.
 */
public class UserInfo {
    private String userId;
    private String userName;
    private String email;

    public UserInfo() {
    }

    public UserInfo(String userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
