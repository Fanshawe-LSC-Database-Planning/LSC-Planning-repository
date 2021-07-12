package com.fanshawe.nfttracker.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.fanshawe.nfttracker.api.entities.Term;
import com.fanshawe.nfttracker.api.repositories.TermRepository;
import com.fanshawe.nfttracker.api.request.TermRequest;
import com.fanshawe.nfttracker.api.request.TermSheetRequest;
import com.fanshawe.nfttracker.api.response.TermResponse;
import com.fanshawe.nfttracker.api.services.TermService;

@Controller
public class TermUIController {

	@Autowired
	TermService termService;

	@Autowired
	TermRepository termRepository;

	@GetMapping("/addTerm")
	public ModelAndView addTerm() {
		ModelAndView model = new ModelAndView("createTerm");
		if (!model.isEmpty())
			model.addObject("term", new TermRequest());
		return model;

	}

	@GetMapping("/terms")
	public ModelAndView viewTerms() {
		ModelAndView model = new ModelAndView("viewTerms");
		model.addObject("terms", termService.getTerms().getTerms());
		model.addObject("term", new Term());
		return model;
	}

	@PostMapping("/terms")
	public ModelAndView viewAllTerms() {
		ModelAndView model = new ModelAndView("viewTerms");
		model.addObject("terms", termService.getTerms().getTerms());
		model.addObject("term", new Term());
		return model;
	}

	@PostMapping("/termsForCreatingTermSheet")
	public ModelAndView viewTermsForTermSheetPost(Model model1) {
		ModelAndView model = new ModelAndView("createTermsheet");
		if (model1.getAttribute("message") != null) {
			model.addObject("message", model1.getAttribute("message"));
		}
		if (termService.getTerms().getTerms() != null)
			model.addObject("terms", termService.getTerms().getTerms().stream()
					.filter(term -> term.getTermSheet() == null).collect(Collectors.toList()));
		model.addObject("termSheetRequest", new TermSheetRequest());
		return model;
	}

	@PostMapping("/deleteTerm")
	public RedirectView deleteTerm(@ModelAttribute("term") Term term, HttpServletRequest request) {
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		RedirectView model = new RedirectView("terms", true);
		termRepository.deleteById(term.getTermId());
		return model;
	}

	@GetMapping("/termsForCreatingTermSheet")
	public ModelAndView viewTermsForTermSheet(Model model1) {
		ModelAndView model = new ModelAndView("createTermsheet");
		if (model1.getAttribute("message") != null) {
			model.addObject("message", model1.getAttribute("message"));
		}
		if (termService.getTerms().getTerms() != null)
			model.addObject("terms", termService.getTerms().getTerms().stream()
					.filter(term -> term.getTermSheet() == null).collect(Collectors.toList()));
		model.addObject("termSheetRequest", new TermSheetRequest());
		return model;
	}

	@SuppressWarnings("deprecation")
	@PostMapping("/createTerm")
	public ModelAndView createTermInSystem(@ModelAttribute("term") TermRequest term, BindingResult result,
			ModelAndView model) {
		ModelAndView returnModel = new ModelAndView("createTerm");
		if (result.hasErrors()) {
			returnModel.addObject("errorMessage", "Please fill the inputs properly");
		} else {
			if (StringUtils.isEmpty(term.getTermName())) {
				returnModel.addObject("errorMessage", "Please fill the term name");
			} else {
				TermResponse response = new TermResponse();
				response = termService.addTerms(Arrays.asList(term));
				returnModel.addObject("message", response.getMessage());
			}
		}
		return returnModel;
	}

}
