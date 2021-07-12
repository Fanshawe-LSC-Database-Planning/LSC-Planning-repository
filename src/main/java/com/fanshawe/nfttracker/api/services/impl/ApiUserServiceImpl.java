package com.fanshawe.nfttracker.api.services.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fanshawe.nfttracker.api.entities.ApiUser;
import com.fanshawe.nfttracker.api.repositories.ApiUserRepository;
import com.fanshawe.nfttracker.api.request.ApiUserRequest;
import com.fanshawe.nfttracker.api.response.ApiUserResponse;
import com.fanshawe.nfttracker.api.services.ApiUserService;
import com.fanshawe.nfttracker.helper.ApplicationHelper;

/**
 * @author Raman.Ahuja
 * 
 *         User Entity Service implementation for database CRUD layer
 *         interaction
 *
 */
@Service
public class ApiUserServiceImpl implements ApiUserService {

	@Autowired
	private ApiUserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public ApiUserResponse createUser(ApiUserRequest apiUserRequest) {
		ApiUser userEntity = new ApiUser();
		BeanUtils.copyProperties(apiUserRequest, userEntity);
		userEntity.setDateUserAdded(new Date());
		userEntity.setUserId(passwordEncoder.encode(apiUserRequest.getUsername()));
		userEntity.setPassword(passwordEncoder.encode(apiUserRequest.getPassword()));
		ApiUser userEntityReturned = userRepository.save(userEntity);
		ApiUserResponse apiUserResponse = new ApiUserResponse();
		if (userEntityReturned != null) {
			apiUserResponse.setStatus(Boolean.TRUE);
			apiUserResponse.setMessage("User created successfully");
			BeanUtils.copyProperties(userEntityReturned, apiUserResponse);
			return apiUserResponse;
		}
		ApplicationHelper.configureFailureResponseWithoutException(apiUserResponse,
				"Error occured while creating user");
		return apiUserResponse;
	}

	@Override
	public ApiUserResponse getUser(String username) {
		Optional<ApiUser> user = userRepository.findUserByUsername(username);
		ApiUserResponse apiUserResponse = new ApiUserResponse();
		if (user != null && user.isPresent()) {
			apiUserResponse.setStatus(Boolean.TRUE);
			apiUserResponse.setMessage("User fetched successfully");
			BeanUtils.copyProperties(user.get(), apiUserResponse);
			return apiUserResponse;
		}
		ApplicationHelper.configureFailureResponseWithoutException(apiUserResponse,
				"Error occured while fetching user");
		return apiUserResponse;
	}

}
