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

import com.fanshawe.nfttracker.api.request.ProgramLevelRequest;
import com.fanshawe.nfttracker.api.request.ProgramRequest;
import com.fanshawe.nfttracker.api.response.ProgramResponse;
import com.fanshawe.nfttracker.api.services.ProgramService;

@RestController
@RequestMapping("/api/v1/programs")
public class ProgramController {

	@Autowired
	ProgramService programService;

	@PostMapping
	public ProgramResponse addProgram(@RequestBody List<ProgramRequest> programRequests) {
		return programService.addProgram(programRequests);
	}

	@GetMapping
	public ProgramResponse getAllPrograms() {
		return programService.getPrograms();
	}

	@GetMapping("/{programCode}")
	public ProgramResponse getProgramByProgramCode(@PathVariable String programCode) {
		return programService.getProgram(programCode);
	}

	@PutMapping("/{programCode}")
	public ProgramResponse updateProgramByProgramCode(@PathVariable String programCode,
			@RequestBody ProgramRequest programRequest) {
		return programService.updateProgram(programCode, programRequest);
	}

	@DeleteMapping("/{programCode}")
	public ProgramResponse deleteProgramByProgramCode(@PathVariable String programCode) {
		return programService.deleteProgram(programCode);
	}

	@PostMapping("/{programCode}/programlevels/")
	public ProgramResponse addProgramLevelToProgram(@PathVariable String programCode,
			@RequestBody List<ProgramLevelRequest> programLevelRequests) {
		return programService.addProgramLevelToProgram(programCode, programLevelRequests);
	}

	@DeleteMapping("/{programCode}/programlevels/")
	public ProgramResponse removeProgramLevelToProgram(@PathVariable String programCode,
			@RequestBody List<ProgramLevelRequest> programLevelRequests) {
		return programService.removeProgramLevelToProgram(programCode, programLevelRequests);
	}
}
