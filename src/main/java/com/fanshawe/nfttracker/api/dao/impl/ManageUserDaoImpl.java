package com.fanshawe.nfttracker.api.dao.impl;

import com.fanshawe.nfttracker.api.dao.ManageUserDao;
import com.fanshawe.nfttracker.api.entities.ApiUser;
import com.fanshawe.nfttracker.api.entities.Professor;
import com.fanshawe.nfttracker.api.entities.User;
import com.fanshawe.nfttracker.api.repositories.ManageUserRepository;
import com.fanshawe.nfttracker.api.request.ApiUserRequest;
import com.fanshawe.nfttracker.api.request.ManageUserRequest;
import com.fanshawe.nfttracker.api.request.ProfessorRequest;
import com.fanshawe.nfttracker.api.response.ApiUserResponse;
import com.fanshawe.nfttracker.api.response.ManagerUserResponse;
import com.fanshawe.nfttracker.api.response.ProfessorResponse;
import com.fanshawe.nfttracker.helper.ApplicationHelper;
import com.fanshawe.nfttracker.helper.CopyListProperties;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class ManageUserDaoImpl implements ManageUserDao {

        @Autowired
        private ManageUserRepository manageUserRepo;

    @Autowired
    PasswordEncoder passwordEncoder;
       /* @Override
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
*/
    @Override
    public ManagerUserResponse addUser(ManageUserRequest manageUser) throws Exception {
        User userEntity = new User();
        BeanUtils.copyProperties(manageUser, userEntity);
        userEntity.setDateUserAdded(new Date());
        userEntity.setUserId(passwordEncoder.encode(manageUser.getUsername()));
        userEntity.setPassword(passwordEncoder.encode(manageUser.getPassword()));
        User userEntityReturned = manageUserRepo.save(userEntity);
        ManagerUserResponse userResponse= new ManagerUserResponse();
        if (userEntityReturned != null) {
            userResponse.setStatus(Boolean.TRUE);
            userResponse.setMessage("User created successfully");
            BeanUtils.copyProperties(userEntityReturned, userResponse);
            return userResponse;
        }
        ApplicationHelper.configureFailureResponseWithoutException(userResponse,
                "Error occured while creating user");
        return userResponse;
    }


        @Override
        public ManagerUserResponse getUser(String username) {
            Optional<User> user = manageUserRepo.findUserByUsername(username);
            ManagerUserResponse userResponse = new ManagerUserResponse();
            if (user != null && user.isPresent()) {
                userResponse.setStatus(Boolean.TRUE);
                userResponse.setMessage("User fetched successfully");
                BeanUtils.copyProperties(user.get(), userResponse);
                return userResponse;
            }
            ApplicationHelper.configureFailureResponseWithoutException(userResponse,
                    "Error occured while fetching user");
            return userResponse;
        }



       /* @Override
        public ManagerUserResponse deleteUser(String username) {
            List<User> userSaved = manageUserRepo.findByUsername(username);
            ManagerUserResponse response = new ManagerUserResponse();
            response.setUsers;
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
                    "User details not found in the database for username:" + username);
            return response;

        }*/
}
