package com.fanshawe.nfttracker.controller;

import com.fanshawe.nfttracker.api.request.ManageUserRequest;
import com.fanshawe.nfttracker.api.response.ManagerUserResponse;
import com.fanshawe.nfttracker.api.services.ManageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller

public class ManageUserUI {

        @Autowired
        private ManageUserService userService;


        @GetMapping("/createUser")
        public ModelAndView addUser() {
            ModelAndView model = new ModelAndView("manageUser");
            if (!model.isEmpty())
                model.addObject("user", new ManageUserRequest());
            return model;

        }


        @PostMapping  ("/createUserInSystem")
        @ResponseBody
        public ModelAndView addUserToDB(@ModelAttribute("user") ManageUserRequest manageUser, BindingResult result,
                ModelAndView model, HttpServletRequest req){
            ModelAndView model1 = new ModelAndView("manageUser");
            if (result.hasErrors()) {
                result.getAllErrors().forEach(error -> {
                });
                return new ModelAndView("error");
            }
            ManagerUserResponse response = new ManagerUserResponse();
            response = userService.addUser(manageUser);
            model1.addObject("user", new ManageUserRequest());

            model1.addObject("message", response.getMessage());
            model1.setViewName("manageUser");
            return model1;
        }
    }
