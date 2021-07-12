package com.fanshawe.nfttracker.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fanshawe.nfttracker.api.entities.ApiUser;
import com.fanshawe.nfttracker.api.response.ApiUserResponse;
import com.fanshawe.nfttracker.api.services.ApiUserService;
import com.fanshawe.nfttracker.helper.ReadApplicationSettings;

@Controller
public class LoginUIController {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	ApiUserService apiUserService;

	@PostMapping("/performLogin")
	public ModelAndView performLoginAuthentication(@ModelAttribute("apiUser") ApiUser apiUser, HttpServletRequest req) {
		ApiUserResponse apiUserResponse = apiUserService.getUser(apiUser.getUsername());
		if (apiUserResponse != null && apiUserResponse.isStatus()) {
			if (encoder.matches(apiUser.getPassword().toString(), apiUserResponse.getPassword())) {
				ModelAndView model = new ModelAndView("index");
				HttpSession session = req.getSession(false);
				try {
					if (!ReadApplicationSettings.loadPropertiesFile()) {
						ModelAndView propertyErrorModel = new ModelAndView("login");
						propertyErrorModel.addObject("loginMessage",
								"Please check the application setting file, there's seems to some error in the properties");
						return propertyErrorModel;
					}
				} catch (RuntimeException e) {
					ModelAndView propertyErrorModel = new ModelAndView("login");
					propertyErrorModel.addObject("loginMessage",
							"Please check the application setting file, there's seems to some error in the properties");
					return propertyErrorModel;
				}
				if (session != null) {
					session.setAttribute("userId", apiUserResponse.getUserId());
					session.setAttribute("username", apiUserResponse.getUsername());
					return model;
				} else {
					return new ModelAndView("error");
				}
			} else {
				ModelAndView model = new ModelAndView("login");
				model.addObject("loginMessage", "Incorrect Password!");
				return model;
			}
		} else {
			ModelAndView model = new ModelAndView("login");
			model.addObject("loginMessage", "Incorrect Username!");
			return model;
		}
	}

	@GetMapping("/")
	public ModelAndView getLogonPage() {
		ModelAndView model = new ModelAndView("login");
		model.addObject("apiUser", new ApiUser());
		return model;

	}

	@GetMapping("/home")
	public ModelAndView getHomePage() {
		return new ModelAndView("index");

	}

	@GetMapping("/performLogout")
	public ModelAndView logoutFromPage(HttpServletRequest req) {
		if (req.getSession() != null) {
			req.getSession().removeAttribute("userId");
			req.getSession().removeAttribute("username");
		}
		ModelAndView model = new ModelAndView("login");
		model.addObject("apiUser", new ApiUser());
		model.addObject("loginMessage", "Logout Successfully!");
		return model;

	}

	@GetMapping("/endSession")
	public ModelAndView endSession() {
		ModelAndView model = new ModelAndView("login");
		model.addObject("apiUser", new ApiUser());
		model.addObject("loginMessage", "Session Ended, Please login!");
		return model;

	}

}
