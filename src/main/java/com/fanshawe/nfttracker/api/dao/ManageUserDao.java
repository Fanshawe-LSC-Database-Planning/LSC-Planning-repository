package com.fanshawe.nfttracker.api.dao;

import com.fanshawe.nfttracker.api.request.ApiUserRequest;
import com.fanshawe.nfttracker.api.request.ManageUserRequest;
import com.fanshawe.nfttracker.api.response.ApiUserResponse;
import com.fanshawe.nfttracker.api.response.ManagerUserResponse;

import java.util.List;

public interface ManageUserDao {

    public ManagerUserResponse addUser(List<ManageUserRequest> manageUser) throws Exception;

    public ManagerUserResponse getUser();

    public ManagerUserResponse getUser(String username);


    public ManagerUserResponse deleteUser(String username);

}
