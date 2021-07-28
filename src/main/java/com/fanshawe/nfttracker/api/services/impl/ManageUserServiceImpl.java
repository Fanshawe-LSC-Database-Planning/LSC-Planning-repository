package com.fanshawe.nfttracker.api.services.impl;

import com.fanshawe.nfttracker.api.dao.ManageUserDao;
import com.fanshawe.nfttracker.api.request.ManageUserRequest;
import com.fanshawe.nfttracker.api.response.ManagerUserResponse;
import com.fanshawe.nfttracker.api.services.ManageUserService;
import com.fanshawe.nfttracker.helper.ApplicationHelper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ManageUserServiceImpl implements ManageUserService {
    ManageUserDao userDao;
    @Override
    public ManagerUserResponse addUser(List<ManageUserRequest> manageUser) {
        ManagerUserResponse response = new ManagerUserResponse();
        try {
            response = userDao.addUser(manageUser);
        } catch (Exception e) {
            ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
        }
        return response;    }

    @Override
    public ManagerUserResponse getUser() {

        ManagerUserResponse response = new ManagerUserResponse();
        try {
            response = userDao.getUser();
        } catch (Exception e) {
            ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
        }
        return response;
    }

    @Override
    public ManagerUserResponse getUser(String username) {
        ManagerUserResponse response = new ManagerUserResponse();
        try {
            response = userDao.getUser(username);
        } catch (Exception e) {
            ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
        }
        return response;
    }

    @Override
    public ManagerUserResponse deleteUser(String username) {
        ManagerUserResponse response = new ManagerUserResponse();
        try {
            response = userDao.deleteUser(username);
        } catch (Exception e) {
            ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
        }
        return response;
    }
}


