package com.fanshawe.nfttracker.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fanshawe.nfttracker.api.entities.Program;
import com.fanshawe.nfttracker.api.entities.ProgramLevel;
import com.fanshawe.nfttracker.api.entities.TermBlock;
import com.fanshawe.nfttracker.api.repositories.ProgramLevelRepository;
import com.fanshawe.nfttracker.api.repositories.ProgramRepository;
import com.fanshawe.nfttracker.api.repositories.TermBlockRepository;
import com.fanshawe.nfttracker.api.request.ProgramRequest;
import com.fanshawe.nfttracker.api.response.ProgramResponse;
import com.fanshawe.nfttracker.api.services.ProgramService;
import com.fanshawe.nfttracker.helper.ApplicationConstants;

@Controller
public class ProgramUIController {

	@Autowired
	ProgramService programService;

	@Autowired
	ProgramRepository programRepository;

	@Autowired
	ProgramLevelRepository programLevelRepository;

	@Autowired
	TermBlockRepository termBlockRepository;

	@GetMapping("/addProgram")
	public ModelAndView openCreateProgramPage() {
		ModelAndView model = new ModelAndView("addProgram");
		model.addObject("program", new ProgramRequest());
		return model;
	}

	@GetMapping("/viewPrograms")
	public ModelAndView viewPrograms() {
		ModelAndView model = new ModelAndView("viewPrograms");
		model.addObject("programs", programService.getPrograms().getPrograms());
		model.addObject("program", new Program());
		return model;
	}

	@ResponseBody
	@PostMapping("/deleteProgram/{programId}")
	public String deleteProgram(@PathVariable("programId") Long programId, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		try {
			Optional<Program> program = programRepository.findById(programId);
			termBlockRepository.deleteAll(termBlockRepository.findByProgramName(program.get().getProgramName()));
			programRepository.deleteById(programId);
		} catch (Exception e) {
			return "false";
		}
		return "true";
	}

	@PostMapping("/manageProgram")
	public ModelAndView manageProgram(@ModelAttribute("program") Program program, Model model1) {
		ModelAndView model = new ModelAndView("manageProgram");
		if (model1.getAttribute("programId") != null) {
			model.addObject("program",
					programRepository.findById(Long.parseLong(model1.getAttribute("programId").toString())).get());
		} else {
			model.addObject("program", programRepository.findById(program.getProgramId()).get());
		}
		return model;
	}

	@PostMapping("/createProgramInSystem")
	public ModelAndView addFacultytoDB(@ModelAttribute("program") ProgramRequest program, BindingResult result,
			ModelAndView model) {
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> {
			});
			return new ModelAndView("error");
		}
		ProgramResponse response = new ProgramResponse();
		if (program.getProgramCode() == null || program.getProgramName() == null) {
			response.setMessage("Please fill all mandatory parameters");
		} else {
			response = programService.addProgram(Arrays.asList(program));
		}
		if (response.getErrors() == null) {
			model.addObject("message", response.getMessage());
		} else if (response.getMessage().contains("exists")) {
			model.addObject("message", response.getMessage());
		} else {
			model.addObject("message", ApplicationConstants.API_ERROR);
		}
		model.setViewName("addProgram");
		model.addObject("program", new ProgramRequest());
		return model;
	}

	@PostMapping("/deleteProgramLevel/{programId}/{programLevelId}")
	public RedirectView deleteProgramLevel(@PathVariable("programId") Long programId,
			@PathVariable("programLevelId") Long programLevelId, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		List<TermBlock> termBlocks = termBlockRepository
				.findByLevelName(programLevelRepository.findById(programLevelId).get().getLevelName());
		termBlockRepository.deleteAll(termBlocks);
		programLevelRepository.deleteById(programLevelId);
		RedirectView model = new RedirectView("/manageProgram", true);
		redirectAttributes.addFlashAttribute("programId", programId);
		return model;
	}

	@PostMapping("/addProgramLevel/{programId}")
	public RedirectView addProgramLevel(@PathVariable("programId") Long programId,
			@RequestParam("levelName") String levelName, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		RedirectView model = new RedirectView("/manageProgram", true);
		if (levelName != null && levelName.length() > 0) {
			boolean exist = false;
			for (ProgramLevel level : programRepository.findById(programId).get().getLevels()) {
				if (level.getLevelName().equals(levelName)) {
					exist = true;
					break;
				}
			}
			if (exist) {
				redirectAttributes.addFlashAttribute("addprogramLevelMessage", "Please fill the different level name!");
			} else {
				ProgramLevel programLevel = new ProgramLevel();
				programLevel.setLevelName(levelName);
				programLevelRepository.save(programLevel);
				programLevelRepository.addProgramLevelInProgram(programId, Arrays.asList(levelName));
			}
		} else {
			redirectAttributes.addFlashAttribute("addProgramLevelMessage", "Please fill the level name!");
		}
		redirectAttributes.addFlashAttribute("programId", programId);
		return model;
	}

}
