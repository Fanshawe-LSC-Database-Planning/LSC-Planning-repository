package com.fanshawe.nfttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fanshawe.nfttracker.api.services.ApiUserService;
import com.fanshawe.nfttracker.helper.ApplicationConstants;
import com.fanshawe.nfttracker.helper.ReadApplicationSettings;

@Controller
public class SettingsController {

	@Autowired
	ApiUserService apiUserService;

	@GetMapping("/settings")
	public ModelAndView viewSettings() {
		ModelAndView model = new ModelAndView("settings");
		model.addObject(ApplicationConstants.FROM_EMAIL,
				ReadApplicationSettings.getApplicationProperty(ApplicationConstants.FROM_EMAIL));
		model.addObject(ApplicationConstants.FROM_EMAIL_PASSWORD,
				ReadApplicationSettings.getApplicationProperty(ApplicationConstants.FROM_EMAIL_PASSWORD));
		model.addObject("smtpHost", ReadApplicationSettings.getApplicationProperty(ApplicationConstants.SMTP_HOST));
		model.addObject("smtpPort", ReadApplicationSettings.getApplicationProperty(ApplicationConstants.SMTP_PORT));
		model.addObject(ApplicationConstants.PART_TIME_HOURS,
				ReadApplicationSettings.getApplicationProperty(ApplicationConstants.PART_TIME_HOURS));
		model.addObject(ApplicationConstants.PARTIAL_LOAD_HOURS,
				ReadApplicationSettings.getApplicationProperty(ApplicationConstants.PARTIAL_LOAD_HOURS));
		return model;

	}

	@SuppressWarnings("deprecation")
	@ResponseBody
	@PostMapping("/saveSettings")
	public String viewSettings(@RequestParam("email") String fromEmailUsername,
			@RequestParam("password") String password, @RequestParam("smtpHost") String smtpHost,
			@RequestParam("smtpPort") Long smtpPort, @RequestParam("partTimeHours") Long partTimeHours,
			@RequestParam("partialLoadHours") Long partialLoadHours) {
		if (StringUtils.isEmpty(fromEmailUsername) || StringUtils.isEmpty(password) || smtpHost == null
				|| smtpPort == null || partTimeHours == null || partialLoadHours == null) {
			return "false";
		} else {
			ReadApplicationSettings.setApplicationProperty(ApplicationConstants.FROM_EMAIL, fromEmailUsername);
			ReadApplicationSettings.setApplicationProperty(ApplicationConstants.FROM_EMAIL_PASSWORD, password);
			ReadApplicationSettings.setApplicationProperty(ApplicationConstants.SMTP_HOST, smtpHost);
			ReadApplicationSettings.setApplicationProperty(ApplicationConstants.SMTP_PORT, smtpPort.toString());
			ReadApplicationSettings.setApplicationProperty(ApplicationConstants.PARTIAL_LOAD_HOURS,
					partialLoadHours.toString());
			ReadApplicationSettings.setApplicationProperty(ApplicationConstants.PART_TIME_HOURS,
					partTimeHours.toString());
			ReadApplicationSettings.savePropertiesFile();
		}
		return "true";

	}

}
