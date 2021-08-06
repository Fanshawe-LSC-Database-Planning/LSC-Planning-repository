package com.fanshawe.nfttracker.api.controller;

import com.fanshawe.nfttracker.api.entities.User;
import com.fanshawe.nfttracker.api.repositories.ManageUserRepository;
import com.fanshawe.nfttracker.api.request.ManageUserRequest;
import com.fanshawe.nfttracker.api.response.ManagerUserResponse;
import com.fanshawe.nfttracker.api.services.ManageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
class WebController {

    @Autowired
    PasswordEncoder passwordEncoder;

    private ManageUserRepository userRepository;

    @GetMapping(path="/addUser")
    public @ResponseBody String addUser(@RequestParam String username, @RequestParam String password) {
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        userRepository.save(u);
        return "SUCCESS!";
    }
}
/*@Controller
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
    public ManagerUserResponse addUsers(@RequestBody ManageUserRequest user) {
        return userService.addUser(user);
    }

    @GetMapping("/{username}")
    public ManagerUserResponse getUser(@PathVariable String username) {
        return userService.getUser(username);

    }
}*/

