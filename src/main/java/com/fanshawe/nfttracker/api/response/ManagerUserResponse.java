package com.fanshawe.nfttracker.api.response;

import com.fanshawe.nfttracker.api.entities.ApiUser;
import com.fanshawe.nfttracker.api.entities.Professor;
import com.fanshawe.nfttracker.api.entities.User;

import java.util.ArrayList;
import java.util.List;


public class ManagerUserResponse extends ApiResponse {
    private String userId;
    private String username;
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "ApiUserResponse [userId=" + userId + ", username=" + username + ", password=" + password + "]";
    }

}
