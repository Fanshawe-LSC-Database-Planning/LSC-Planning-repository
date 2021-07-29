package com.fanshawe.nfttracker.api.controller;

import com.fanshawe.nfttracker.api.request.ManageUserRequest;
import com.fanshawe.nfttracker.api.response.ManagerUserResponse;
import com.fanshawe.nfttracker.api.services.ManageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userManage")
public class UserController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(ManageUserService userService) {
        this.userService = userService;
    }

    private ManageUserService userService;

    @PostMapping
    public ManagerUserResponse createUsers(@RequestBody List<ManageUserRequest> user) {
        return userService.addUser(user);
    }

    @GetMapping("/{username}")
    public ManagerUserResponse getUser(@PathVariable String username) {
        return userService.getUser(username);

    }
}

