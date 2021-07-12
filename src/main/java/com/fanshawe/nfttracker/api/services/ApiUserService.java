package com.fanshawe.nfttracker.api.services;

import com.fanshawe.nfttracker.api.request.ApiUserRequest;
import com.fanshawe.nfttracker.api.response.ApiUserResponse;

/**
 * @author Raman.Ahuja
 * 
 *         User Entity Service Interface for database CRUD layer interaction
 *
 */
public interface ApiUserService {

	public ApiUserResponse createUser(ApiUserRequest apiUser);

	public ApiUserResponse getUser(String emailId);

}
