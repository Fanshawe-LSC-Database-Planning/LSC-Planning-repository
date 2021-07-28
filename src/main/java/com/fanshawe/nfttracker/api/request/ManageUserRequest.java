package com.fanshawe.nfttracker.api.request;

import com.fanshawe.nfttracker.api.entities.Course;
import io.jsonwebtoken.lang.Strings;

import java.util.List;

public class ManageUserRequest {

    private String username;

    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password;
    }
    public Boolean validateMandatoryParameters() {
        if (Strings.hasText(getUsername()) && Strings.hasText(getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ManageUserRequest [username=" + username + ", password=" + password + "]";
    }

}

