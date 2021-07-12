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
import com.fanshawe.nfttracker.api.request.ProgramLevelRequest;
import com.fanshawe.nfttracker.api.response.ProgramLevelResponse;
import com.fanshawe.nfttracker.api.services.ProgramLevelService;

@RestController
@RequestMapping("/api/v1/programlevels")
public class ProgramLevelController {

	@Autowired
	ProgramLevelService programLevelService;

	@PostMapping
	public ProgramLevelResponse addProgramLevel(@RequestBody List<ProgramLevelRequest> programLevelRequests) {
		return programLevelService.addProgramLevel(programLevelRequests);
	}

	@GetMapping
	public ProgramLevelResponse getAllProgramLevels() {
		return programLevelService.getProgramLevels();
	}

	@GetMapping("/{programLevelName}")
	public ProgramLevelResponse getProgramLevelByProgramLevelName(@PathVariable String programLevelName) {
		return programLevelService.getProgramLevel(programLevelName);
	}

	@PutMapping("/{programLevelName}")
	public ProgramLevelResponse updateProgramLevelByProgramLevelName(@PathVariable String programLevelName,
			@RequestBody ProgramLevelRequest programLevelRequest) {
		return programLevelService.updateProgramLevel(programLevelName, programLevelRequest);
	}

	@DeleteMapping("/{programLevelName}")
	public ProgramLevelResponse deleteProgramLevelByProgramLevelName(@PathVariable String programLevelName) {
		return programLevelService.deleteProgramLevel(programLevelName);
	}

	@PostMapping("/{programLevelName}/courses/")
	public ProgramLevelResponse addCoursesToProgramLevel(@PathVariable String programLevelName,
			@RequestBody List<CourseRequest> courseRequests) {
		return programLevelService.addCourseToProgramLevel(programLevelName, courseRequests);
	}

	@DeleteMapping("/{programLevelName}/courses/")
	public ProgramLevelResponse removeCoursesToProgramLevel(@PathVariable String programLevelName,
			@RequestBody List<CourseRequest> courseRequests) {
		return programLevelService.removeCourseFromProgramLevel(programLevelName, courseRequests);
	}
}
