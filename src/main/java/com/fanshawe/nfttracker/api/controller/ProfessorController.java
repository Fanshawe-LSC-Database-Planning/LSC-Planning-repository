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

import com.fanshawe.nfttracker.api.request.CourseRequest;
import com.fanshawe.nfttracker.api.request.ProfessorRequest;
import com.fanshawe.nfttracker.api.response.ProfessorResponse;
import com.fanshawe.nfttracker.api.services.ProfessorService;

@RestController
@RequestMapping("/api/v1/professors")
public class ProfessorController {

	@Autowired
	ProfessorService professorService;

	@PostMapping
	public ProfessorResponse addProfesssor(@RequestBody List<ProfessorRequest> professorRequests) {
		return professorService.addProfessor(professorRequests);
	}

	@GetMapping
	public ProfessorResponse getAllProfessors() {
		return professorService.getProfessors();
	}

	@GetMapping("/{emailId}")
	public ProfessorResponse getProfessorByEmailId(@PathVariable String emailId) {
		return professorService.getProfessor(emailId);
	}

	@PutMapping("/{emailId}")
	public ProfessorResponse updateProfessorByEmailId(@PathVariable Long professorId,
			@RequestBody ProfessorRequest professorRequest) {
		return professorService.updateProfessor(professorId, professorRequest);
	}

	@DeleteMapping("/{emailId}")
	public ProfessorResponse deleteProfessorByEmailId(@PathVariable String emailId) {
		return professorService.deleteProfessor(emailId);
	}

	@PostMapping("/{emailId}/courses/")
	public ProfessorResponse addSuggestedCoursesToProfessor(@PathVariable String emailId,
			@RequestBody List<CourseRequest> courseRequests) {
		return professorService.assignCourseToProfessor(emailId, courseRequests);
	}

	@DeleteMapping("/{emailId}/courses/")
	public ProfessorResponse removeSuggestedCoursesFromProfessor(@PathVariable String emailId,
			@RequestBody List<CourseRequest> courseRequests) {
		return professorService.deassignCourseFromProfessor(emailId, courseRequests);
	}
}
