package com.fanshawe.nfttracker.api.services.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fanshawe.nfttracker.api.entities.ApiUser;
import com.fanshawe.nfttracker.api.repositories.ApiUserRepository;
import com.fanshawe.nfttracker.api.security.model.MyUserDetails;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	ApiUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<ApiUser> user = userRepository.findUserByUsername(username);
		MyUserDetails userDetails = new MyUserDetails();
		BeanUtils.copyProperties(user.get(), userDetails);
		return userDetails;
	}

}