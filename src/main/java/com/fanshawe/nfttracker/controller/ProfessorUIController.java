package com.fanshawe.nfttracker.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fanshawe.nfttracker.api.entities.Course;
import com.fanshawe.nfttracker.api.entities.Professor;
import com.fanshawe.nfttracker.api.repositories.CourseRepository;
import com.fanshawe.nfttracker.api.request.CourseRequest;
import com.fanshawe.nfttracker.api.request.ProfessorRequest;
import com.fanshawe.nfttracker.api.response.ProfessorResponse;
import com.fanshawe.nfttracker.api.services.ProfessorService;

@Controller
public class ProfessorUIController {

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private CourseRepository courseRepo;

	@GetMapping("/createProfessor")
	public ModelAndView addFaculty() {
		ModelAndView model = new ModelAndView("addProfessor");
		if (!model.isEmpty())
			model.addObject("professor", new ProfessorRequest());
		return model;

	}

	@GetMapping("/professors")
	public ModelAndView viewFaculties() {
		ModelAndView model = new ModelAndView("viewProfessors");
		model.addObject("professors", professorService.getProfessors().getProfessors());
		model.addObject("professorRequest", new ProfessorRequest());
		return model;
	}

	@PostMapping("/assignDeassignCourses")
	public ModelAndView addCoursesToProfessorPage(@ModelAttribute("professorRequest") ProfessorRequest professorRequest,
			HttpServletRequest request, Model model) {
		String profEmailId;

		if (model.asMap().get("selectedProfessor") != null) {
			profEmailId = ((Professor) model.asMap().get("selectedProfessor")).getEmailId();
		} else {
			profEmailId = professorRequest.getEmailId();
		}
		ModelAndView model1 = new ModelAndView("assignDeassignCourses");
		if (model.asMap().get("courseDeletionMessage") != null) {
			model1.addObject("message", model.asMap().get("courseDeletionMessage").toString());
		} else if (model.asMap().get("courseAssigningMessage") != null) {
			model1.addObject("message", model.asMap().get("courseAssigningMessage").toString());
		}
		model1.addObject("assignedCourseToDelete", new CourseRequest());
		model1.addObject("selectedCoursesToAssign", new CourseRequest());
		Professor professor = professorService.getProfessor(profEmailId).getProfessors().get(0);
		if (professor.getSuggestedCourses() != null && professor.getSuggestedCourses().size() >= 0) {
			List<Object[]> programToCourses = courseRepo.findAllUnAssignedCourses(professor.getProfessorId());
			if (programToCourses != null && programToCourses.size() > 0) {
				model1.addObject("unAssignedCourses", getUnassignedCourses(programToCourses));
				model1.addObject("selectedProfessor", professor);
				model1.addObject("assignedCourses", professor.getSuggestedCourses());
				return model1;
			}
		}
		model1.addObject("unAssignedCourses", new ArrayList<>());
		model1.addObject("selectedProfessor", professor);
		model1.addObject("assignedCourses", professor.getSuggestedCourses());
		return model1;
	}

	/**
	 * @param availableProgramWithUnAssignedCourse
	 */
	private Map<String, List<Course>> getUnassignedCourses(List<Object[]> programToCourses) {
		Map<String, List<Course>> programWithCourses = new HashMap<String, List<Course>>();
		for (Object[] object : programToCourses) {
			String key = object[0].toString() + ":" + object[1].toString();
			if (programWithCourses.containsKey(key)) {
				List<Course> courses = programWithCourses.get(key);
				Course tempCourse = new Course();
				tempCourse.setCourseCode(object[2].toString());
				tempCourse.setCourseName(object[3].toString());
				courses.add(tempCourse);
				programWithCourses.put(key, courses);
			} else {
				List<Course> courses = new ArrayList<Course>();
				Course tempCourse = new Course();
				tempCourse.setCourseCode(object[2].toString());
				tempCourse.setCourseName(object[3].toString());
				courses.add(tempCourse);
				programWithCourses.put(key, courses);
			}

		}
		return programWithCourses;
	}

	@PostMapping("/deassignCoursesFromProfessor")
	public RedirectView deassignCoursesFromProfessor(
			@ModelAttribute("assignedCourseToDelete") CourseRequest assignedCourseToDelete,
			@ModelAttribute("selectedCoursesToAssign") CourseRequest selectedCoursesToAssign,
			HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		ProfessorResponse response = professorService.deassignCourseFromProfessor(
				assignedCourseToDelete.getCourseName(), Arrays.asList(assignedCourseToDelete));
		RedirectView view = new RedirectView("/assignDeassignCourses", true);
		Professor selectedProfessor = new Professor();
		selectedProfessor.setEmailId(assignedCourseToDelete.getCourseName());
		redirectAttributes.addFlashAttribute("selectedProfessor", selectedProfessor);
		redirectAttributes.addFlashAttribute("courseDeletionMessage", response.getMessage());
		return view;
	}

	@PostMapping("/assignCoursesToProfessor")
	public RedirectView assignCoursesToProfessor(
			@ModelAttribute("assignedCourseToDelete") CourseRequest assignedCourseToDelete,
			@ModelAttribute("selectedCoursesToAssign") CourseRequest selectedCoursesToAssign,
			HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) throws Exception {
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		RedirectView view = new RedirectView("/assignDeassignCourses", true);
		Professor selectedProfessor = new Professor();
		selectedProfessor.setEmailId(selectedCoursesToAssign.getCourseName());
		redirectAttributes.addFlashAttribute("selectedProfessor", selectedProfessor);
		if (StringUtils.hasText(selectedCoursesToAssign.getCourseCode())) {
			String[] courseCodes = selectedCoursesToAssign.getCourseCode().split(",");
			List<CourseRequest> courses = new ArrayList<CourseRequest>();
			ProfessorResponse response = null;
			if (courseCodes.length > 0) {
				for (int i = 0; i < courseCodes.length; i++) {
					CourseRequest course = new CourseRequest();
					course.setCourseCode(courseCodes[i].trim());
					courses.add(course);
				}
				response = professorService.assignCourseToProfessor(selectedCoursesToAssign.getCourseName(), courses);
			}
			redirectAttributes.addFlashAttribute("courseAssigningMessage", response.getMessage());
		} else {
			redirectAttributes.addFlashAttribute("courseAssigningMessage", "Please select the course to be assigned!");
		}
		return view;
	}

	@PostMapping("/createProfessorInSystem")
	public ModelAndView addFacultytoDB(@ModelAttribute("professor") ProfessorRequest professor, BindingResult result,
			ModelAndView model) {
		ModelAndView model1 = new ModelAndView("addProfessor");
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> {
			});
			return new ModelAndView("error");
		}
		ProfessorResponse response = new ProfessorResponse();
		if (professor.validateMandatoryParameters()) {
			response = professorService.addProfessor(Arrays.asList(professor));
			model1.addObject("professor", new ProfessorRequest());
		} else {
			model1.addObject("professor", professor);
			response.setMessage("Please fill all mandatory parameters");
		}
		model1.addObject("message", response.getMessage());
		model1.setViewName("addProfessor");
		return model1;
	}

	@PostMapping("/updateFaculty")
	public ModelAndView updateFaculty(@ModelAttribute("professor") ProfessorRequest professorRequest,
			HttpServletRequest request, Model model) {
		String profEmailId;
		if (model.asMap().get("professorEmailId") != null) {
			profEmailId = model.asMap().get("professorEmailId").toString();
		} else {
			profEmailId = professorRequest.getEmailId();
		}

		Professor professor = professorService.getProfessor(profEmailId).getProfessors().get(0);
		ModelAndView model1 = new ModelAndView("updateProfessor");
		model1.addObject("professor", professor);
		// model.addAttribute("professor", professor);
		if (model.asMap().get("errorMessage") != null) {
			model.addAttribute("errorMessage", model.asMap().get("errorMessage").toString());
		}
		model1.setViewName("updateProfessor");
		return model1;
	}

	@PostMapping("/updateFacultyInSystem/{professorId}")
	public RedirectView updateFacultyInSystem(@PathVariable("professorId") Long professorId,
			@ModelAttribute("professor") ProfessorRequest professorRequest, HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		RedirectView viewProfessorsModel = new RedirectView("/viewProfessorUpdateStatus", true);
		RedirectView updateProfessorModel = new RedirectView("/updateFaculty", true);
		ProfessorResponse response = new ProfessorResponse();
		if (professorRequest.validateMandatoryParameters()) {
			response = professorService.updateProfessor(professorId, professorRequest);
			if (response.isStatus()) {
				redirectAttributes.addFlashAttribute("updateFacultyMessage", "Professor details updated!");
				return viewProfessorsModel;
			} else if (response.getErrors() != null) {
				redirectAttributes.addFlashAttribute("professorEmailId", professorRequest.getEmailId());
				redirectAttributes.addFlashAttribute("errorMessage",
						"Some error occured while updating professor details, please try again!");
				// updateProfessorModel.addObject("professor", professorRequest);
				// updateProfessorModel.addObject("errorMessage",
				// "Some error occured while updating professor details, please try again!");
				return updateProfessorModel;
			} else {
				redirectAttributes.addFlashAttribute("professorEmailId", professorRequest.getEmailId());
				redirectAttributes.addFlashAttribute(response.getMessage());
				// updateProfessorModel.addObject("professor", professorRequest);
				// updateProfessorModel.addObject("errorMessage", response.getMessage());
				return updateProfessorModel;
			}
		} else {
			redirectAttributes.addFlashAttribute("professorEmailId", professorRequest.getEmailId());
			redirectAttributes.addFlashAttribute("Please fill all mandatory parameters");
			// updateProfessorModel.addObject("professor", professorRequest);
			// updateProfessorModel.addObject("errorMessage", "Please fill all mandatory
			// parameters");
			return updateProfessorModel;
		}
	}

	@PostMapping("/viewProfessorUpdateStatus")
	public ModelAndView viewUpdateAddProfessorStatus() {
		ModelAndView model = new ModelAndView("viewProfessorUpdateStatus");
		return model;
	}

	@GetMapping("/professorsToUpdate")
	public ModelAndView viewFacultiesToUpdate() {
		ModelAndView model = new ModelAndView("viewProfessorsToUpdate");
		model.addObject("professors", professorService.getProfessors().getProfessors());
		model.addObject("professor", new ProfessorRequest());
		return model;
	}
}
