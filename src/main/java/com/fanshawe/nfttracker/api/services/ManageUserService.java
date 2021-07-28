package com.fanshawe.nfttracker.api.services;

import com.fanshawe.nfttracker.api.request.ApiUserRequest;
import com.fanshawe.nfttracker.api.request.CourseRequest;
import com.fanshawe.nfttracker.api.request.ManageUserRequest;
import com.fanshawe.nfttracker.api.request.ProfessorRequest;
import com.fanshawe.nfttracker.api.response.ApiUserResponse;
import com.fanshawe.nfttracker.api.response.ManagerUserResponse;
import com.fanshawe.nfttracker.api.response.ProfessorResponse;

import java.util.List;

public interface ManageUserService {



        public ManagerUserResponse addUser(List<ManageUserRequest> manageUser);

        public ManagerUserResponse getUser();

        public ManagerUserResponse getUser(String username);


        public ManagerUserResponse deleteUser(String username);



    }


