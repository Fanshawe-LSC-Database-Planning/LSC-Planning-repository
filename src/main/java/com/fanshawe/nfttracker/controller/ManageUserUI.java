package com.fanshawe.nfttracker.controller;

import com.fanshawe.nfttracker.api.request.ManageUserRequest;
import com.fanshawe.nfttracker.api.response.ManagerUserResponse;
import com.fanshawe.nfttracker.api.services.ManageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@RestController

public class ManageUserUI {

        @Autowired
        private ManageUserService userService;

    @RequestMapping("/manageUser")
    public ModelAndView registerPage() {
        ModelAndView model = new ModelAndView("manageUser");
        model.addObject("User", new ManageUserRequest());
        return model;
    }
        @GetMapping("/createUser")
        public ModelAndView addUser () {
            ModelAndView model = new ModelAndView("manageUser");
            if (!model.isEmpty())
                model.addObject("User", new ManageUserRequest());
            return model;

        }


        @PostMapping("/createUserInSystem")
        @ResponseBody
        public ModelAndView addUserToDB(@ModelAttribute("User") ManageUserRequest manageUser, BindingResult result,
                ModelAndView model){
            ModelAndView model1 = new ModelAndView("manageUser");
            if (result.hasErrors()) {
                result.getAllErrors().forEach(error -> {
                });
                return new ModelAndView("error");
            }
            ManagerUserResponse response = new ManagerUserResponse();
            response = userService.addUser(Arrays.asList(manageUser));
            model1.addObject("User", new ManageUserRequest());

            model1.addObject("message", response.getMessage());
            model1.setViewName("manageUser");
            return model1;
        }
    }