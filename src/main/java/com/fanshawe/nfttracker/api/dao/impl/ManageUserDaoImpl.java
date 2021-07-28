package com.fanshawe.nfttracker.api.dao.impl;

import com.fanshawe.nfttracker.api.dao.ManageUserDao;
import com.fanshawe.nfttracker.api.entities.ApiUser;
import com.fanshawe.nfttracker.api.entities.Professor;
import com.fanshawe.nfttracker.api.entities.User;
import com.fanshawe.nfttracker.api.repositories.ManageUserRepository;
import com.fanshawe.nfttracker.api.request.ManageUserRequest;
import com.fanshawe.nfttracker.api.request.ProfessorRequest;
import com.fanshawe.nfttracker.api.response.ManagerUserResponse;
import com.fanshawe.nfttracker.api.response.ProfessorResponse;
import com.fanshawe.nfttracker.helper.ApplicationHelper;
import com.fanshawe.nfttracker.helper.CopyListProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManageUserDaoImpl implements ManageUserDao {

        @Autowired
        private ManageUserRepository manageUserRepo;


        @Override
        public ManagerUserResponse addUser(List<ManageUserRequest> UserRequests) throws Exception {
            List<User> userEntities = new CopyListProperties(User.class).copy(UserRequests);
            List<User> userSavedEntities = (List<User>) manageUserRepo.saveAll(userEntities);
            ManagerUserResponse response = new ManagerUserResponse();
            response.setUsers(new ArrayList<>());
            if (userSavedEntities != null) {
                response.setStatus(Boolean.TRUE);
                response.setUsers(new CopyListProperties(ManageUserRequest.class).copy(userSavedEntities));
                response.setMessage("User details saved successfully");
                return response;
            }
            ApplicationHelper.configureFailureResponseWithoutException(response,
                    "Some error occured while saving professor details");
            return response;

        }

        @Override
        public ManagerUserResponse getUser() {
            List<User> users = (List<User>) manageUserRepo.findAll();
            ManagerUserResponse response = new ManagerUserResponse();
            response.setUsers(new ArrayList<>());
            if (users != null && users.size() > 0) {
                response.setStatus(Boolean.TRUE);
                response.setUsers(new CopyListProperties(ManageUserRequest.class).copy(users));
                response.setMessage("User details fetched successfully");
                return response;
            }
            ApplicationHelper.configureFailureResponseWithoutException(response,
                    "No User details found in the database");
            return response;
        }

        @Override
        public ManagerUserResponse getUser(String username) {
            List<User> users = (List<User>) manageUserRepo.findByUsername(username);
            ManagerUserResponse response = new ManagerUserResponse();
            response.setUsers(new ArrayList<>());
            if (users != null && users.size() > 0) {
                response.setStatus(Boolean.TRUE);
                response.setUsers(users);
                response.setMessage("User details fetched successfully");
                return response;
            }
            ApplicationHelper.configureFailureResponseWithoutException(response,
                    "User details not found in the database for emailId:" + username);
            return response;
        }



        @Override
        public ManagerUserResponse deleteUser(String username) {
            List<User> userSaved = manageUserRepo.findByUsername(username);
            ManagerUserResponse response = new ManagerUserResponse();
            response.setUsers(new ArrayList<>());
            if (userSaved != null && userSaved.size() > 0) {
                response.setStatus(Boolean.TRUE);
                manageUserRepo.delete(userSaved.get(0));
            List<User> deletedUserDetails = new ArrayList<User>();
                deletedUserDetails.add(userSaved.get(0));
                response.setUsers(new CopyListProperties(ManageUserRequest.class).copy(deletedUserDetails));
                response.setMessage("User deleted successfully");
                return response;
            }
            ApplicationHelper.configureFailureResponseWithoutException(response,
                    "User details not found in the database for emailId:" + username);
            return response;

        }
}
