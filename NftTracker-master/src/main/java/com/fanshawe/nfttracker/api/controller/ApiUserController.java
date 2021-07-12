package com.fanshawe.nfttracker.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fanshawe.nfttracker.api.request.ApiUserRequest;
import com.fanshawe.nfttracker.api.response.ApiUserResponse;
import com.fanshawe.nfttracker.api.services.ApiUserService;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	public ApiUserController(ApiUserService userService) {
		this.userService = userService;
	}

	private ApiUserService userService;

	@PostMapping
	public ApiUserResponse createUsers(@RequestBody ApiUserRequest user) {
		return userService.createUser(user);
	}

	@GetMapping("/{username}")
	public ApiUserResponse getUser(@PathVariable String username) {
		return userService.getUser(username);

	}

}
