package com.fanshawe.nfttracker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

import com.fanshawe.nfttracker.api.entities.Course;
import com.fanshawe.nfttracker.api.entities.CourseInfo;
import com.fanshawe.nfttracker.api.entities.Program;
import com.fanshawe.nfttracker.api.entities.ProgramLevel;
import com.fanshawe.nfttracker.api.entities.TermBlock;
import com.fanshawe.nfttracker.api.repositories.CourseInfoRepository;
import com.fanshawe.nfttracker.api.repositories.CourseRepository;
import com.fanshawe.nfttracker.api.repositories.ProfessorRepository;
import com.fanshawe.nfttracker.api.repositories.ProgramLevelRepository;
import com.fanshawe.nfttracker.api.repositories.ProgramRepository;
import com.fanshawe.nfttracker.api.repositories.TermBlockRepository;
import com.fanshawe.nfttracker.api.services.ProgramService;
import com.google.gson.Gson;

@Controller
public class ProgramLevelUIController {

	@Autowired
	ProgramService programService;

	@Autowired
	ProgramRepository programRepository;

	@Autowired
	ProfessorRepository professorRepository;

	@Autowired
	ProgramLevelRepository programLevelRepository;

	@Autowired
	TermBlockRepository termBlockRepository;

	@Autowired
	CourseInfoRepository courseInfoRepository;

	@Autowired
	CourseRepository courseRepository;

	@GetMapping("/viewProgramLevels")
	public ModelAndView viewProgramLevels() {
		ModelAndView model = new ModelAndView("viewProgramLevels");
		model.addObject("programs", programService.getPrograms().getPrograms());
		return model;
	}

	@PostMapping("/manageProgramLevel/{programId}/{programLevelId}")
	public ModelAndView manageProgramLevel(@PathVariable("programId") Long programId,
			@PathVariable("programLevelId") Long programLevelId, Model model1) {
		ModelAndView model = new ModelAndView("manageProgramLevel");
		Program program;
		// program =
		// programRepository.findById(Long.parseLong(model1.getAttribute("programId").toString())).get();
		program = programRepository.findById(programId).get();
		model.addObject("programName", program.getProgramName());
		model.addObject("programCode", program.getProgramCode());
		model.addObject("programId", program.getProgramId());
		ProgramLevel level = program.getLevels().stream()
				.filter(line -> programLevelId.equals(line.getProgramLevelId())).findFirst().orElse(null);
		model.addObject("programlevel", level);
		return model;
	}

	@ResponseBody
	@GetMapping("/loadLevelsByProgramId/{programId}")
	public String getLevelByProgram(@PathVariable("programId") Long programId) {
		Gson gson = new Gson();
		List<ProgramLevel> levels = new ArrayList<ProgramLevel>();
		Optional<Program> program = programRepository.findById(programId);
		if (program.isPresent()) {
			for (ProgramLevel level : program.get().getLevels()) {
				ProgramLevel levelToAdd = new ProgramLevel();
				levelToAdd.setProgramLevelId(level.getProgramLevelId());
				levelToAdd.setLevelName((level.getLevelName()));
				levels.add(levelToAdd);
			}

		}
		return gson.toJson(levels);
	}

	@PostMapping("/addCourse/{programId}/{programLevelId}")
	public RedirectView addCourse(@PathVariable("programId") Long programId,
			@PathVariable("programLevelId") Long programLevelId, @RequestParam("courseName") String courseName,
			@RequestParam("courseCode") String courseCode, @RequestParam("courseCredit") Long courseCredit,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		RedirectView model = new RedirectView("/manageProgramLevel/" + programId + "/" + programLevelId, true);
		if ((courseName != null && courseName.length() > 0) && (courseCode != null && courseCode.length() > 0)
				&& (courseCredit != null)) {
			List<Course> courses = courseRepository.findByCourseCode(courseCode);
			ProgramLevel programLevel = programLevelRepository.findById(programLevelId).get();
			for (Course course : courses) {
				if (course.getCourseCode().equals(courseCode)) {
					redirectAttributes.addFlashAttribute("addCourseMessage",
							"This course code already exists in this level or some other program!");
					return model;
				}
			}
			// saving in the courseInfo table
			List<TermBlock> termBlocks = termBlockRepository.findByLevelName(programLevel.getLevelName());
			if (termBlocks != null && termBlocks.size() > 0) {
				List<CourseInfo> courseInfos = termBlocks.get(0).getListOfCourses();
				int sectionCount = 1;
				if (courseInfos.size() > 0) {
					sectionCount = Integer.parseInt(
							courseInfoRepository.countCourseInfoByTermBlockId(termBlocks.get(0).getTermBlockId(),
									courseInfos.get(0).getCourseCode())[0].toString());
				}
				for (int i = 0; i < sectionCount; i++) {
					CourseInfo ci = new CourseInfo();
					ci.setCourseCode(courseCode);
					ci.setCourseName(courseName);
					ci.setCourseCredit(courseCredit);
					courseInfos.add(ci);
				}
				termBlocks.get(0).setListOfCourses(courseInfos);
				termBlockRepository.save(termBlocks.get(0));

			}

			// saving in the course table
			courseRepository.addCourseToProgramLevel(programLevelId, courseName, courseCode, courseCredit);

		} else {
			redirectAttributes.addFlashAttribute("addCourseMessage", "Please fill all the fields!");
		}

		return model;
	}

	@PostMapping("/deleteCourse/{programId}/{programLevelId}/{courseId}")
	public RedirectView deleteCourse(@PathVariable("programId") Long programId,
			@PathVariable("programLevelId") Long programLevelId, @PathVariable("courseId") Long courseId,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		List<CourseInfo> courseInfos = courseInfoRepository
				.findByCourseCode(courseRepository.findById(courseId).get().getCourseCode());
		for (CourseInfo courseInfo : courseInfos) {
			courseRepository.decreaseProfessorHoursOnCourseDeletion(courseInfo.getAssignedProfessors(),
					courseInfo.getCourseCredit());
		}
		courseInfoRepository.deleteAll(courseInfos);
		courseRepository.deleteSuggestedProfessorForCourseId(courseId);
		courseRepository.deleteById(courseId);
		RedirectView model = new RedirectView("/manageProgramLevel/" + programId + "/" + programLevelId, true);
		return model;
	}

	@PostMapping("/editCourse/{programId}/{programLevelId}/{courseId}")
	public ModelAndView editCourse(@PathVariable("programId") Long programId,
			@PathVariable("programLevelId") Long programLevelId, @PathVariable("courseId") Long courseId) {
		ModelAndView model = new ModelAndView("/editCourse");
		model.addObject("programId", programId);
		model.addObject("programLevelId", programLevelId);
		model.addObject("course", courseRepository.findById(courseId).get());
		return model;
	}

	@PostMapping("/updateCourse/{programId}/{programLevelId}/{courseId}")
	public RedirectView updateCourse(@PathVariable("programId") Long programId,
			@PathVariable("programLevelId") Long programLevelId, @PathVariable("courseId") Long courseId,
			@ModelAttribute("course") Course course, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		RedirectView model = new RedirectView("/manageProgramLevel/" + programId + "/" + programLevelId, true);
		Optional<Course> courseInDb = courseRepository.findById(courseId);
		boolean courseCreditChangeFlag = false;
		if (courseInDb.get().getCourseCredit() == course.getCourseCredit()) {
			courseCreditChangeFlag = true;
		}
		// updating the course Infos
		List<CourseInfo> courseInfos = courseInfoRepository.findByCourseCode(courseInDb.get().getCourseCode());
		for (CourseInfo courseInfo : courseInfos) {
			courseInfo.setCourseCode(course.getCourseCode());
			courseInfo.setCourseName(course.getCourseName());
			if (!courseCreditChangeFlag) {
				courseRepository.updateProfessorHoursOnCourseDeletion(courseInfo.getAssignedProfessors(),
						course.getCourseCredit() - courseInfo.getCourseCredit());
				courseInfo.setCourseCredit(course.getCourseCredit());
			}

		}
		courseInfoRepository.saveAll(courseInfos);

		// updating the actual course object
		courseInDb.get().setCourseCode(course.getCourseCode());
		courseInDb.get().setCourseName(course.getCourseName());
		courseInDb.get().setCourseCredit(course.getCourseCredit());
		courseRepository.save(courseInDb.get());

		return model;
	}
}
