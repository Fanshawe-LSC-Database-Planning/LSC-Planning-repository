package com.fanshawe.nfttracker.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fanshawe.nfttracker.api.request.TermRequest;
import com.fanshawe.nfttracker.api.response.TermResponse;
import com.fanshawe.nfttracker.api.services.TermService;

@RestController
@RequestMapping("/api/v1/terms")

public class TermController {

	@Autowired
	TermService termService;

	@PostMapping
	public TermResponse addTerm(@RequestBody List<TermRequest> termRequests) {
		TermResponse response = null;
		try {
			response = termService.addTerms(termRequests);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(Boolean.FALSE);
		}
		return response;
	}

	@GetMapping
	public TermResponse getAllCourses() {
		return termService.getTerms();
	}

	@GetMapping("/{termName}")
	public TermResponse getTermByName(@PathVariable String termName) {
		return termService.getTermByTermName(termName);
	}

	@PutMapping("/{termName}")
	public TermResponse updateTermByTermName(@PathVariable String termName, @RequestBody TermRequest termRequest) {
		return termService.updateTermName(termRequest, termName);
	}

	@DeleteMapping("/{termName}")
	public TermResponse deleteTermByTermName(@PathVariable String termName) {
		return termService.deleteTerm(termName);
	}
}
