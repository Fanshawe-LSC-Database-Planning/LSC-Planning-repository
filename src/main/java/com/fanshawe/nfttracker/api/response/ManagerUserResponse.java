package com.fanshawe.nfttracker.api.response;

import com.fanshawe.nfttracker.api.entities.ApiUser;
import com.fanshawe.nfttracker.api.entities.Professor;
import com.fanshawe.nfttracker.api.entities.User;

import java.util.ArrayList;
import java.util.List;

public class ManagerUserResponse extends ApiResponse {
    private List<User> users = new ArrayList<User>();

    public java.util.List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "ManageUserResponse [users=" + users + "]";
    }

}
