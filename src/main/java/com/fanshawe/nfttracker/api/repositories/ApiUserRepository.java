package com.fanshawe.nfttracker.api.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fanshawe.nfttracker.api.entities.ApiUser;

@Repository
public interface ApiUserRepository extends CrudRepository<ApiUser, Long> {

	public Optional<ApiUser> findUserByUserId(String emailId);

	public Optional<ApiUser> findUserByUsername(String username);

}
