package com.fanshawe.nfttracker.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fanshawe.nfttracker.api.entities.CourseInfo;
import com.fanshawe.nfttracker.api.entities.Professor;
import com.fanshawe.nfttracker.api.entities.ProfessorStatus;
import com.fanshawe.nfttracker.api.entities.Program;
import com.fanshawe.nfttracker.api.entities.ProgramLevel;
import com.fanshawe.nfttracker.api.entities.Term;
import com.fanshawe.nfttracker.api.entities.TermBlock;
import com.fanshawe.nfttracker.api.entities.TermSheet;
import com.fanshawe.nfttracker.api.repositories.CourseInfoRepository;
import com.fanshawe.nfttracker.api.repositories.ProfessorRepository;
import com.fanshawe.nfttracker.api.repositories.ProgramRepository;
import com.fanshawe.nfttracker.api.repositories.TermBlockRepository;
import com.fanshawe.nfttracker.api.repositories.TermRepository;
import com.fanshawe.nfttracker.api.repositories.TermSheetRepository;
import com.fanshawe.nfttracker.api.request.TermSheetRequest;
import com.fanshawe.nfttracker.api.services.CourseService;
import com.fanshawe.nfttracker.api.services.EmailService;
import com.fanshawe.nfttracker.api.services.ExcelExportService;
import com.fanshawe.nfttracker.api.services.ProgramService;
import com.fanshawe.nfttracker.api.services.TermService;
import com.fanshawe.nfttracker.helper.ApplicationConstants;
import com.fanshawe.nfttracker.helper.ReadApplicationSettings;
import com.google.gson.Gson;

@Controller
public class TermSheetUIController {

	@Autowired
	private TermService termService;

	@Autowired
	private ProgramService programService;

	@Autowired
	private ProgramRepository programRepo;

	@Autowired
	private TermSheetRepository termSheetRepository;

	@Autowired
	private TermRepository termRepository;

	@Autowired
	CourseInfoRepository courseInfoRepository;

	@Autowired
	ProfessorRepository professorRepository;

	@Autowired
	CourseService courseService;

	@Autowired
	ExcelExportService excelExportService;

	@Autowired
	ReadApplicationSettings readApplicationSettings;

	private Map<String, ArrayList<String>> programLevelsSelected;
	private Map<String, ArrayList<String>> programLevelsAvailable;

	@Autowired
	private TermBlockRepository termBlockRepo;

	@GetMapping("/viewTermSheets")
	public ModelAndView viewTermsForTermSheet(Model model1) {
		ModelAndView model = new ModelAndView("viewTermSheets");
		model.addObject("termSheets", (List<TermSheet>) termSheetRepository.findAll());
		model.addObject("termSheet", new TermSheet());
		return model;
	}

	@PostMapping("/viewAllTermsheets")
	public ModelAndView viewAllTermsForTermSheet(Model model1) {
		ModelAndView model = new ModelAndView("viewTermSheets");
		model.addObject("termSheets", (List<TermSheet>) termSheetRepository.findAll());
		model.addObject("termSheet", new TermSheet());
		return model;
	}

	@SuppressWarnings("deprecation")
	@PostMapping("/editTermSheet")
	public ModelAndView openCreateEditTermsheetPage(@ModelAttribute("termSheet") TermSheet termSheet,
													HttpServletRequest request, Model model) {
		ModelAndView model1 = new ModelAndView("editTermSheet");
		model1.addObject("termSheetRequest", new TermSheetRequest());
		List<TermSheet> sheets;
		if (termSheet != null && !StringUtils.isEmpty(termSheet.getTermSheetId()))
			sheets = termSheetRepository.findByTermSheetId(termSheet.getTermSheetId());
		else
			sheets = termSheetRepository
					.findByTermSheetId(((TermSheet) model.getAttribute("termSelected")).getTermSheetId());
		if (sheets != null && sheets.size() > 0 && sheets.get(0) != null) {
			programLevelsSelected = new HashMap<String, ArrayList<String>>();
			for (TermBlock termBlock : sheets.get(0).getListOfTermBlocks()) {
				if (programLevelsSelected.containsKey(termBlock.getProgramName())) {
					List<String> levels = programLevelsSelected.get(termBlock.getProgramName());
					levels.add(termBlock.getLevelName());
					programLevelsSelected.put(termBlock.getProgramName(), (ArrayList<String>) levels);
				} else {
					List<String> levels = new ArrayList<String>();
					levels.add(termBlock.getLevelName());
					programLevelsSelected.put(termBlock.getProgramName(), (ArrayList<String>) levels);
				}
			}
			if (model.containsAttribute("termSelected")) {
				model1.addObject("termSelected", model.getAttribute("termSelected"));
			} else {
				TermSheet termSheetSelected = new TermSheet();
				termSheetSelected.setTermSheetId(sheets.get(0).getTermSheetId());
				termSheetSelected.setTermSheetName(sheets.get(0).getTermSheetName());
				termSheetSelected.setTermName(sheets.get(0).getTermName());
				model1.addObject("termSelected", termSheetSelected);
			}
			model1.addObject("termBlocks", sheets.get(0).getListOfTermBlocks());
			model1.addObject("termBlock", new TermBlock());
		}
		return model1;
	}

	@PostMapping("/deleteTermSheet")
	public RedirectView deleteTermsheet(@ModelAttribute("termSheet") TermSheet termSheet, HttpServletRequest request,
										Model model) {
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		RedirectView model1 = new RedirectView("viewAllTermsheets", true);
		List<Term> terms = (List<Term>) termRepository.findAll();
		for (Term term : terms) {
			if (term.getTermSheet().getTermSheetId() == termSheet.getTermSheetId()) {
				termRepository.unlinkTermSheetToTerm(term.getTermId());
				break;
			}
		}
		termSheetRepository.deleteById(termSheet.getTermSheetId());
		return model1;
	}

	@ResponseBody
	@GetMapping("/loadLevelByProgram/{programName}")
	public String getLevelByProgram(@PathVariable("programName") String programName) {
		Gson gson = new Gson();
		List<String> levels = new ArrayList<String>();
		if (programLevelsAvailable != null && programLevelsAvailable.size() > 0) {
			levels = programLevelsAvailable.get(programName);
		}
		System.out.println(programLevelsAvailable);
		return gson.toJson(levels);
	}

	@PostMapping("/editTermSheet/{termName}/{termSheetId}/{termSheetName}")
	public RedirectView goBackToEditTermSheet(@PathVariable("termName") String termName,
											  @PathVariable("termSheetId") Long termSheetId, @PathVariable("termSheetName") String termSheetName,
											  @ModelAttribute("termBlock") TermBlock termBlock, BindingResult result, HttpServletRequest request,
											  Model model1, RedirectAttributes redirectAttributes) {
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		RedirectView view = new RedirectView("/editTermSheet", true);
		TermSheet termSheet = new TermSheet();
		termSheet.setTermName(termName);
		termSheet.setTermSheetId(termSheetId);
		termSheet.setTermSheetName(termSheetName);
		redirectAttributes.addFlashAttribute("termSelected", termSheet);
		redirectAttributes.addFlashAttribute("termSheet", new TermSheet());
		redirectAttributes.addFlashAttribute("termBlock", new TermBlock());
		return view;
	}

	@PostMapping("/createTermSheet")
	public RedirectView createTermSheet(@ModelAttribute("termSheetRequest") TermSheetRequest termSheetRequest,
										HttpServletRequest request) {
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		RedirectView model = new RedirectView("termsForCreatingTermSheet", true);
		TermSheet sheet = new TermSheet();
		BeanUtils.copyProperties(termSheetRequest, sheet);
		TermSheet sheetSaved = termSheetRepository.save(sheet);
		if (termSheetRepository.save(sheet) != null) {
			termRepository.linkTermSheetToTerm(sheetSaved.getTermSheetId(), termSheetRequest.getTermName());
		}
		return model;
	}

	@PostMapping("/addTermBlock/{termSheetId}")
	public ModelAndView showPrograms(@PathVariable("termSheetId") Long termSheetId,
									 @ModelAttribute("termSheet") TermSheet termSheet, BindingResult result, Model model1) {
		programLevelsAvailable = new HashMap<String, ArrayList<String>>();
		ModelAndView model = new ModelAndView("addTermBlock");
		List<Program> programs = programService.getPrograms().getPrograms();
		if (programs != null && programs.size() > 0) {
			evaluateAvailableProgramAndLevels(programs);
		}
		model.addObject("programs", programLevelsAvailable);
		model.addObject("termSheetName", termSheet.getTermSheetName());
		model.addObject("termName", termSheet.getTermName());
		model.addObject("termSheetId", termSheetId);
		model.addObject("termBlock", new TermBlock());
		return model;
	}

	private void evaluateAvailableProgramAndLevels(List<Program> programs) {
		for (Program program : programs) {
			if (program.getLevels() != null && program.getLevels().size() > 0) {
				if (programLevelsSelected.containsKey(program.getProgramName())) {
					for (ProgramLevel programLevel : program.getLevels()) {
						if (programLevelsSelected.get(program.getProgramName()) != null && programLevel != null
								&& !programLevelsSelected.get(program.getProgramName())
								.contains(programLevel.getLevelName())) {
							List<String> levels;
							if (programLevelsAvailable.containsKey(program.getProgramName())) {
								levels = programLevelsAvailable.get(program.getProgramName());
								levels.add(programLevel.getLevelName());
							} else {
								levels = new ArrayList<String>();
								levels.add(programLevel.getLevelName());
							}
							programLevelsAvailable.put(program.getProgramName(), (ArrayList<String>) levels);
						}
					}
				} else {
					programLevelsAvailable.put(program.getProgramName(), (ArrayList<String>) program.getLevels()
							.stream().map(object -> object.getLevelName()).collect(Collectors.toList()));
				}
			}
		}
	}

	@PostMapping("/addTermBlockToTermsheet/{termName}/{termSheetId}/{termSheetName}")
	public RedirectView addTermBlockToTermsheet(
			@RequestParam("termStartDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date termStartDate,
			@RequestParam("termEndDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date termEndDate,
			@PathVariable("termName") String termName, @PathVariable("termSheetName") String termSheetName,
			@PathVariable("termSheetId") Long termSheetId, @ModelAttribute("termBlock") TermBlock termBlock,
			BindingResult result, HttpServletRequest request, Model model1, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			result.getAllErrors().toString();
		}
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		RedirectView view = new RedirectView("/editTermSheet", true);
		termBlock.setTermEndDate(termEndDate);
		termBlock.setTermStartDate(termStartDate);
		List<CourseInfo> coursesInfo = addCoursesInfosToTermBlock(termBlock);
		termBlock.setListOfCourses(coursesInfo);
		TermBlock block = termBlockRepo.save(termBlock);
		termSheetRepository.linkTermBlockToTermSheet(block.getTermBlockId(), termSheetName);
		TermSheet termSheet = new TermSheet();
		termSheet.setTermName(termName);
		termSheet.setTermSheetName(termSheetName);
		termSheet.setTermSheetId(termSheetId);
		redirectAttributes.addFlashAttribute("termSelected", termSheet);
		redirectAttributes.addFlashAttribute("termSheet", new TermSheet());
		redirectAttributes.addFlashAttribute("termBlock", new TermBlock());
		return view;
	}

	@PostMapping("/deleteTermBlock/{termName}/{termSheetName}/{termSheetId}/{termBlockId}")
	public RedirectView deleteTermBlock(@PathVariable("termName") String termName,
										@PathVariable("termSheetId") Long termSheetId, @PathVariable("termSheetName") String termSheetName,
										@PathVariable("termBlockId") String termBlockId, HttpServletRequest request, Model model1,
										@ModelAttribute("termBlock") TermBlock termBlock, BindingResult result,
										RedirectAttributes redirectAttributes) {
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		RedirectView view = new RedirectView("/editTermSheet", true);
		List<TermSheet> termSheets = termSheetRepository.findByTermSheetId(termSheetId);
		Optional<TermBlock> termBlockFromDb = termBlockRepo.findById(Long.parseLong(termBlockId));
		termBlockFromDb.get().getListOfCourses().forEach(course -> {
			course.getAssignedProfessors().forEach(profId -> {
				Long profHours = termSheets.get(0).getProfessorAllotedHours().get(profId);
				if (null != profHours) {
					if (profHours - course.getCourseCredit() == 0) {
						termSheets.get(0).getProfessorAllotedHours().remove(profId);
					} else {
						termSheets.get(0).getProfessorAllotedHours().put(profId, profHours - course.getCourseCredit());
					}
				}
			});
		});
		int index = -1;
		for (int i = 0; i < termSheets.get(0).getListOfTermBlocks().size(); i++) {
			if (termSheets.get(0).getListOfTermBlocks().get(i).getTermBlockId() == Long.parseLong(termBlockId)) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			termSheets.get(0).getListOfTermBlocks().remove(index);
		}
		termSheetRepository.save(termSheets.get(0));
		termBlockRepo.deleteById(Long.parseLong(termBlockId));
		TermSheet termSheet = new TermSheet();
		termSheet.setTermName(termName);
		termSheet.setTermSheetName(termSheetName);
		termSheet.setTermSheetId(termSheetId);
		redirectAttributes.addFlashAttribute("termSelected", termSheet);
		redirectAttributes.addFlashAttribute("termSheet", new TermSheet());
		redirectAttributes.addFlashAttribute("termBlock", new TermBlock());
		return view;
	}

	@PostMapping("/updateTermBlock/{termName}/{termSheetName}/{termBlockId}/{termSheetId}")
	public RedirectView saveEditedTermBlock(
			@RequestParam("termStartDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date termStartDate,
			@RequestParam("termEndDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date termEndDate,
			@PathVariable("termName") String termName, @PathVariable("termSheetName") String termSheetName,
			@PathVariable("termBlockId") String termBlockId, @PathVariable("termSheetId") Long termSheetId,
			HttpServletRequest request, Model model1, @ModelAttribute("termBlockToEdit") TermBlock termBlockToEdit,
			BindingResult result, RedirectAttributes redirectAttributes) {
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		RedirectView view = new RedirectView("/editTermSheet", true);
		TermBlock termBlockToUpdate = termBlockRepo.findById(Long.parseLong(termBlockId)).get();
		termBlockToUpdate.setTermEndDate(termEndDate);
		termBlockToUpdate.setTermStartDate(termStartDate);
		termBlockToUpdate.setTotalWeeks(termBlockToEdit.getTotalWeeks());
		termBlockRepo.save(termBlockToUpdate);
		courseInfoRepository.deleteUnlinkedCourseInfos();
		TermSheet termSheet = new TermSheet();
		termSheet.setTermName(termName);
		termSheet.setTermSheetName(termSheetName);
		termSheet.setTermSheetId(termSheetId);
		redirectAttributes.addFlashAttribute("termSelected", termSheet);
		redirectAttributes.addFlashAttribute("termSheet", new TermSheet());
		redirectAttributes.addFlashAttribute("termBlock", new TermBlock());
		return view;
	}

	@PostMapping("/editTermBlock/{termName}/{termSheetName}/{termSheetId}/{termBlockId}")
	public ModelAndView editTermBlock(@PathVariable("termName") String termName,
									  @PathVariable("termSheetName") String termSheetName, @PathVariable("termBlockId") String termBlockId,
									  @PathVariable("termSheetId") Long termSheetId, Model model1) {
		ModelAndView view = new ModelAndView("/editTermBlock");
		Optional<TermBlock> termBlockToEdit = termBlockRepo.findById(Long.parseLong(termBlockId));
		if (termBlockToEdit != null) {
			view.addObject("termBlock", termBlockToEdit.get());
		} else {
			view.addObject("termBlock", new TermBlock());
		}
		view.addObject("termName", termName);
		view.addObject("termSheetName", termSheetName);
		view.addObject("termSheetId", termSheetId);
		view.addObject("termBlockToEdit", new TermBlock());
		return view;
	}

	private List<CourseInfo> addCoursesInfosToTermBlock(TermBlock termBlock) {
		List<CourseInfo> coursesInfo = new ArrayList<CourseInfo>();
		if (termBlock != null && termBlock.getProgramName() != null) {
			List<Program> programs = programRepo.findByProgramName(termBlock.getProgramName());
			Optional<ProgramLevel> programLevel = programs.get(0).getLevels().stream()
					.filter(level -> level.getLevelName().equals(termBlock.getLevelName())).findFirst();
			if (programLevel.get().getCourses() != null) {
				programLevel.get().getCourses().forEach(course -> {
					CourseInfo courseInfo = new CourseInfo();
					courseInfo.setCourseCode(course.getCourseCode());
					courseInfo.setCourseName(course.getCourseName());
					courseInfo.setCourseCredit(course.getCourseCredit());
					coursesInfo.add(courseInfo);
				});
			}
		}
		return coursesInfo;
	}

	@PostMapping("/manageTermSheet")
	public ModelAndView viewTermSheet(@ModelAttribute("termSheet") TermSheet termSheet, Model model1) {
		ModelAndView model = new ModelAndView("manageTermSheet");
		List<TermSheet> sheets = new ArrayList<TermSheet>();
		if (termSheet != null && termSheet.getTermSheetId() != null) {
			sheets = termSheetRepository.findByTermSheetId(termSheet.getTermSheetId());
		} else {
			sheets = termSheetRepository
					.findByTermSheetId(Long.parseLong(model1.getAttribute("termSheetId").toString()));
		}
		Map<String, List<Professor>> courseSuggestedProfessor = new HashMap<String, List<Professor>>();
		Map<Long, List<Professor>> courseAssignedProfessor = new HashMap<Long, List<Professor>>();
		if (sheets != null && sheets.size() > 0) {
			sheets.get(0).getListOfTermBlocks().forEach(termBlock -> {
				evaluateSuggestedAssignedProfessor(courseSuggestedProfessor, courseAssignedProfessor, termBlock);
			});
			model.addObject("sheet", sheets.get(0));
		} else {
			model.addObject("sheet", new ArrayList<TermSheet>());
		}
		model.addObject("courseSuggestedProfessor", courseSuggestedProfessor);
		model.addObject("courseAssignedProfessor", courseAssignedProfessor);
		model.addObject("termCourse", new TermBlock());
		return model;
	}

	/**
	 * @param courseSuggestedProfessor
	 * @param courseAssignedProfessor
	 * @param termBlock
	 */
	private void evaluateSuggestedAssignedProfessor(Map<String, List<Professor>> courseSuggestedProfessor,
													Map<Long, List<Professor>> courseAssignedProfessor, TermBlock termBlock) {
		for (CourseInfo courseInfo : termBlock.getListOfCourses()) {
			courseSuggestedProfessor.put(courseInfo.getCourseCode(),
					courseService.getCourse(courseInfo.getCourseName()).getCourse().get(0).getSuggestedProfessors());
			for (Long assinedProf : courseInfo.getAssignedProfessors()) {
				Optional<Professor> professor = professorRepository.findById(assinedProf);
				if (professor.isPresent()) {
					if (courseAssignedProfessor.containsKey(courseInfo.getCourseInfoId())) {
						List<Professor> assignedProfsInMap = new ArrayList<>(
								courseAssignedProfessor.get(courseInfo.getCourseInfoId()));
						assignedProfsInMap.add(professor.get());
						courseAssignedProfessor.put(courseInfo.getCourseInfoId(), assignedProfsInMap);
					} else {
						courseAssignedProfessor.put(courseInfo.getCourseInfoId(), Arrays.asList(professor.get()));
					}
				}

			}
		}
	}

	@PostMapping("/addSectionsToTermBlock/{termSheetId}/{termBlockId}")
	public RedirectView addSectionsToTermBlock(@PathVariable("termSheetId") Long termSheetId,
											   @PathVariable("termBlockId") Long termBlockId, HttpServletRequest request, Model model1,
											   RedirectAttributes redirectAttributes) {
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		RedirectView model = new RedirectView("/manageTermSheet", true);
		Optional<TermBlock> termBlock = termBlockRepo.findById(termBlockId);
		if (termBlock.isPresent()) {
			List<CourseInfo> newCoursesToAdd = addCoursesInfosToTermBlock(termBlock.get());
			if (newCoursesToAdd != null && newCoursesToAdd.size() > 0) {
				termBlock.get().getListOfCourses().addAll(newCoursesToAdd);
				TermBlock termBlockSaved = termBlockRepo.save(termBlock.get());
				if (termBlockSaved != null) {
					redirectAttributes.addFlashAttribute("termSheetId", termSheetId);
					return model;
				}
			}
		}
		redirectAttributes.addFlashAttribute("termSheetId", termSheetId);
		redirectAttributes.addFlashAttribute("errorMessage", "Some Error Occured while adding the sections");
		return model;
	}

	@PostMapping("/saveTermBlock/{termSheetId}")
	public RedirectView viewTermSheet(@PathVariable("termSheetId") Long termSheetId,
									  @ModelAttribute("termCourse") TermBlock termBlock, HttpServletRequest request, Model model1,
									  RedirectAttributes redirectAttributes) {
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		RedirectView model = new RedirectView("/manageTermSheet", true);
		List<CourseInfo> termBlockCoursesToUpdate = termBlock.getListOfCourses();
		Optional<TermBlock> termBlockOptional = termBlockRepo.findById(termBlock.getTermBlockId());
		if (termBlockOptional.isPresent()) {
			List<CourseInfo> termBlockCourses = termBlockOptional.get().getListOfCourses();
			for (int i = 0; i < termBlockCourses.size(); i++) {
				termBlockCourses.get(i).setSectionName(termBlockCoursesToUpdate.get(i).getSectionName());
				termBlockCourses.get(i).setNumberOfStudents(termBlockCoursesToUpdate.get(i).getNumberOfStudents());
				termBlockCourses.get(i).setProgramBlock(termBlockCoursesToUpdate.get(i).getProgramBlock());
				termBlockCourses.get(i).setScheduledRequest((termBlockCoursesToUpdate.get(i).getScheduledRequest()));
			}
			termBlockOptional.get().setListOfCourses(termBlockCourses);
			if (termBlockRepo.save(termBlockOptional.get()) == null) {
				redirectAttributes.addFlashAttribute("errorMessage",
						"Some Error Occured while saving, please save again!");
			}
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Some Error Occured while saving, please save again!");
		}
		redirectAttributes.addFlashAttribute("termSheetId", termSheetId);
		return model;
	}

	@PostMapping("/deleteCoursesFromTermSheet/{termSheetId}")
	public RedirectView deleteCourseFromTermSheet(@PathVariable("termSheetId") Long termSheetId,
												  @ModelAttribute("termCourse") TermBlock termBlock, HttpServletRequest request, Model model1,
												  RedirectAttributes redirectAttributes) {
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		RedirectView model = new RedirectView("/manageTermSheet", true);
		List<CourseInfo> coursesToDelete = new ArrayList<CourseInfo>();
		if (termBlock.getProgramName() != null) {
			String[] courseInfoIds = termBlock.getProgramName().split(",");
			// Long courseCredit = Long.parseLong(termBlock.getLevelName());
			if (courseInfoIds.length > 0) {
				Optional<TermSheet> termSheet = termSheetRepository.findById(termSheetId);
				prepareCoursesToDelete(coursesToDelete, courseInfoIds, termSheet);
				termSheetRepository.save(termSheet.get());
			} else {
				redirectAttributes.addFlashAttribute("errorMessage", "Please select atleast a course to delete!");
			}
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Please select atleast a course to delete!");
		}
		redirectAttributes.addFlashAttribute("termSheetId", termSheetId);
		return model;
	}

	/**
	 * @param coursesToDelete
	 * @param courseInfoIds
	 * @param termSheet
	 */
	private void prepareCoursesToDelete(List<CourseInfo> coursesToDelete, String[] courseInfoIds,
										Optional<TermSheet> termSheet) {
		for (int i = 0; i < courseInfoIds.length; i++) {
			Optional<CourseInfo> course = courseInfoRepository.findById(Long.parseLong(courseInfoIds[i]));
			coursesToDelete.add(course.get());
			for (Long profId : course.get().getAssignedProfessors()) {
				Long profHours = termSheet.get().getProfessorAllotedHours().get(profId);
				if (profHours - course.get().getCourseCredit() == 0) {
					termSheet.get().getProfessorAllotedHours().remove(profId);
				} else {
					termSheet.get().getProfessorAllotedHours().put(profId, profHours - course.get().getCourseCredit());
				}
			}
			courseInfoRepository.deleteAssignedProfessorStatusToCourseInfo(Long.parseLong(courseInfoIds[i]));
			courseInfoRepository.deleteAssignedCoursesToCourseInfo(Long.parseLong(courseInfoIds[i]));
			courseInfoRepository.deleteCourseInfoByCourseInfoId(Long.parseLong(courseInfoIds[i]));
		}
	}

	@PostMapping("/blockProfessorForCourse/{termSheetId}/{courseInfoId}/{courseCredit}")
	public RedirectView blockProfessor(@PathVariable("termSheetId") Long termSheetId,
									   @PathVariable("courseCredit") Long courseCredit, @PathVariable("courseInfoId") Long courseInfoId,
									   @ModelAttribute("termCourse") TermBlock termBlock, HttpServletRequest request, Model model1,
									   RedirectAttributes redirectAttributes) {
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		RedirectView model = new RedirectView("/manageTermSheet", true);
		List<Long> professors = new ArrayList<>();
		for (CourseInfo courseInfo : termBlock.getListOfCourses()) {
			if (courseInfo.getCourseInfoId().equals(courseInfoId)) {
				professors = courseInfo.getAssignedProfessors();
				break;
			}
		}
		Optional<CourseInfo> courseInfo = courseInfoRepository.findById(courseInfoId);
		if (courseInfo.isPresent()) {
			if (!professors.isEmpty()) {
				Optional<TermSheet> termSheet = termSheetRepository.findById(termSheetId);
				courseInfo.get().setAssignedProfessors(professors);
				Map<Long, String> professorsStatus = new HashMap<Long, String>();
				Map<Long, Long> professorHours = termSheet.get().getProfessorAllotedHours();
				for (Long professorId : professors) {
					if (professorHours.containsKey(professorId)) {
						professorHours.put(professorId,
								professorHours.get(professorId) + courseInfo.get().getCourseCredit());
					} else {
						professorHours.put(professorId, courseInfo.get().getCourseCredit());
					}
					professorsStatus.put(professorId, ProfessorStatus.BLOCKED.getStatus());
				}
				termSheet.get().setProfessorAllotedHours(professorHours);
				termSheetRepository.save(termSheet.get());
				courseInfo.get().setProfessorStatus(professorsStatus);
				courseInfoRepository.save(courseInfo.get());
			}
		}
		redirectAttributes.addFlashAttribute("termSheetId", termSheetId);
		redirectAttributes.addFlashAttribute("termCourse", new TermBlock());
		return model;
	}

	@PostMapping("/unblockProfessorForCourse/{termSheetId}/{courseInfoId}/{courseCredit}")
	public RedirectView UnblockProfessor(@PathVariable("termSheetId") Long termSheetId,
										 @PathVariable("courseCredit") Long courseCredit, @PathVariable("courseInfoId") Long courseInfoId,
										 @ModelAttribute("termCourseInfo") TermBlock termBlock, HttpServletRequest request, Model model1,
										 RedirectAttributes redirectAttributes) {
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		RedirectView model = new RedirectView("/manageTermSheet", true);
		List<Long> professors = new ArrayList<>();
		Optional<CourseInfo> courseInfo = courseInfoRepository.findById(courseInfoId);
		if (courseInfo.isPresent()) {
			if (!courseInfo.get().getAssignedProfessors().isEmpty()) {
				Optional<TermSheet> termSheet = termSheetRepository.findById(termSheetId);
				Map<Long, Long> professorHours = termSheet.get().getProfessorAllotedHours();
				for (Long professorId : courseInfo.get().getAssignedProfessors()) {
					if (professorHours.containsKey(professorId))
						if (professorHours.get(professorId) - courseInfo.get().getCourseCredit() == 0)
							professorHours.remove(professorId);
						else
							professorHours.put(professorId,
									professorHours.get(professorId) - courseInfo.get().getCourseCredit());

				}
				courseInfo.get().setAssignedProfessors(professors);
				courseInfo.get().setProfessorStatus(new HashMap<Long, String>());
				courseInfoRepository.save(courseInfo.get());
			}
		}
		redirectAttributes.addFlashAttribute("termSheetId", termSheetId);
		return model;
	}

	@ResponseBody
	@PostMapping("/checkProfessorHours/{termSheetId}/{courseInfoId}/{courseCredit}")
	public String checkProfessorHours(@PathVariable("termSheetId") Long termSheetId,
									  @RequestParam("assignedProfessors") String assignedProfessors,
									  @PathVariable("courseCredit") Long courseCredit, @PathVariable("courseInfoId") Long courseInfoId,
									  HttpServletRequest request) {
		String status = "";
		Optional<TermSheet> termSheet = termSheetRepository.findById(termSheetId);
		if (termSheet.isPresent()) {
			System.out.println(request.getParameter("assignedProfessors"));
			System.out.println(assignedProfessors);
			String[] assignedProfString = assignedProfessors.split(",");
			for (String assignedProfStringId : assignedProfString) {
				Long assignedProf = Long.parseLong(assignedProfStringId);
				if (termSheet.get().getProfessorAllotedHours().containsKey(assignedProf)) {
					Optional<Professor> professor = professorRepository.findById(assignedProf);
					if (professor.isPresent()) {
						if (professor.get().getEmpType().equalsIgnoreCase("Part-time")
								&& termSheet.get().getProfessorAllotedHours().get(assignedProf) + courseCredit > Long
								.parseLong(ReadApplicationSettings
										.getApplicationProperty(ApplicationConstants.PART_TIME_HOURS))) {
							status += "Exceeding Part-time Hours for : " + professor.get().getFirstName() + " "
									+ professor.get().getLastName() + "\n";
						} else if (professor.get().getEmpType().equalsIgnoreCase("Partial Load")
								&& termSheet.get().getProfessorAllotedHours().get(assignedProf) + courseCredit > Long
								.parseLong(ReadApplicationSettings
										.getApplicationProperty(ApplicationConstants.PARTIAL_LOAD_HOURS))) {
							status += "Exceeding Partial Load Hours for : " + professor.get().getFirstName() + " "
									+ professor.get().getLastName() + "\n";
						}
					}
				}
			}

		}
		if (status.equals("")) {
			return "true";
		}
		return status;
	}

	@PostMapping("/viewTermSheetSummary/{termSheetId}")
	public ModelAndView viewSummary(@PathVariable("termSheetId") Long termSheetId, Model modelTemp) {
		ModelAndView model = new ModelAndView("viewTermSheetSummary");
		if (termSheetId == null) {
			termSheetId = (Long) modelTemp.getAttribute("termSheetId");
		}
		List<TermSheet> termSheet = termSheetRepository.findByTermSheetId(termSheetId);
		Map<Professor, List<CourseInfo>> professorToCourseMap = new HashMap<Professor, List<CourseInfo>>();
		Map<Long, Long> professorIdToHoursMap = termSheet.get(0).getProfessorAllotedHours();
		for (TermBlock termBlock : termSheet.get(0).getListOfTermBlocks()) {
			for (CourseInfo courseInfo : termBlock.getListOfCourses()) {
				for (Long profId : courseInfo.getAssignedProfessors()) {
					Optional<Professor> professor = professorRepository.findById(profId);
					if (professorToCourseMap.containsKey(professor.get())) {
						List<CourseInfo> courses = new ArrayList<CourseInfo>(professorToCourseMap.get(professor.get()));
						courses.add(courseInfo);
						professorToCourseMap.put(professor.get(), courses);
					} else {
						professorToCourseMap.put(professor.get(), Arrays.asList(courseInfo));
					}
				}
			}
		}
		model.addObject("sheet", termSheet.get(0));
		model.addObject("professorToCourseMap", professorToCourseMap);
		model.addObject("professorIdToHoursMap", professorIdToHoursMap);
		model.addObject("termBlock", new TermBlock());
		return model;
	}

	@PostMapping("/loiEmailRequest/{professorId}/{termSheetId}")
	public ModelAndView sendLetterOfIntentEmailPage(@PathVariable("professorId") Long professorId,
													@PathVariable("termSheetId") Long termSheetId, @ModelAttribute("termBlock") TermBlock termBlock,
													Model model) {
		ModelAndView sendEmailModel = new ModelAndView("sendLetterOfIntentEmail");
		Optional<Professor> prof = professorRepository.findById(professorId);
		if (prof.isPresent()) {
			model.addAttribute("professor", prof.get());
			model.addAttribute("termSheetId", termSheetId);
			model.addAttribute("fromEmail",
					ReadApplicationSettings.getApplicationProperty(ApplicationConstants.FROM_EMAIL));
			model.addAttribute("date", new SimpleDateFormat("MMMM dd, yyyy").format(new Date()));
		}
		return sendEmailModel;
	}

	@ResponseBody
	@PostMapping("/updateProfessorStatus/{professorId}/{termSheetId}/{status}")
	public String updateProfessorStatus(@PathVariable("professorId") Long professorId,
										@PathVariable("status") String status, @PathVariable("termSheetId") Long termSheetId,
										@ModelAttribute("termBlock") TermBlock termBlock, Model model, HttpServletRequest request) {

		Optional<Professor> prof = professorRepository.findById(professorId);
		if (prof.isPresent()) {
			if (status.equalsIgnoreCase("loi")) {
				termSheetRepository.modifyStatus(professorId, termSheetId, ProfessorStatus.NEW_HIRE.getStatus());
			} else if (status.equalsIgnoreCase("teachingNotification")) {
				termSheetRepository.modifyStatus(professorId, termSheetId,
						ProfessorStatus.TEACHING_NOTIFICATION.getStatus());
			} else if (status.equalsIgnoreCase("contractToFaculty")) {
				termSheetRepository.modifyStatus(professorId, termSheetId,
						ProfessorStatus.CONTRACT_SENT_TO_FACULTY.getStatus());
			} else if (status.equalsIgnoreCase("contractToHR")) {
				termSheetRepository.modifyStatus(professorId, termSheetId,
						ProfessorStatus.CONTRACT_SENT_TO_HR.getStatus());
			} else {
				return "false";
			}
		}
		return "true";
	}

	@PostMapping("/sendContractEmailRequest/{professorId}/{termSheetId}/{empType}")
	public ModelAndView sendContractEmailRequest(@PathVariable("professorId") Long professorId,
												 @PathVariable("empType") String empType, @PathVariable("termSheetId") Long termSheetId,
												 @ModelAttribute("termBlock") TermBlock termBlock, Model model) {
		ModelAndView sendEmailModel = null;
		Optional<Professor> prof = professorRepository.findById(professorId);
		if (prof.isPresent()) {
			if (prof.get().getEmpType().equalsIgnoreCase("Part-time")) {
				sendEmailModel = new ModelAndView("sendPartTimePostSecondaryCreditEmail");
			} else if (prof.get().getEmpType().equalsIgnoreCase("Partial Load")) {
				sendEmailModel = new ModelAndView("sendPartialLoadContractEmail");
			}
			model.addAttribute("professor", prof.get());
			model.addAttribute("termSheetId", termSheetId);
			model.addAttribute("fromEmail",
					ReadApplicationSettings.getApplicationProperty(ApplicationConstants.FROM_EMAIL));
			model.addAttribute("date", new SimpleDateFormat("MMMM dd, yyyy").format(new Date()));
		}
		return sendEmailModel;
	}

	@PostMapping("/teachingNotificationEmailRequest/{professorId}/{termSheetId}")
	public ModelAndView sendTeachingNotificationEmailPage(@PathVariable("professorId") Long professorId,
														  @PathVariable("termSheetId") Long termSheetId, @ModelAttribute("termBlock") TermBlock termBlock,
														  Model model) {
		ModelAndView sendEmailModel = new ModelAndView("sendTeachingNotificationEmail");
		List<TermSheet> termSheet = termSheetRepository.findByTermSheetId(termSheetId);
		Optional<Professor> prof = professorRepository.findById(professorId);
		Map<CourseInfo, String> programToCourse = new HashMap<>();
		if (prof.isPresent() && termSheet.size() > 0) {
			for (TermBlock termBlockTemp : termSheet.get(0).getListOfTermBlocks()) {
				for (CourseInfo course : termBlockTemp.getListOfCourses()) {
					if (course.getAssignedProfessors().contains(professorId)) {
						programToCourse.put(course, termBlockTemp.getProgramName());
					}
				}
			}
			model.addAttribute("programToCourse", programToCourse);
			model.addAttribute("professor", prof.get());
			model.addAttribute("termSheetId", termSheetId);
			model.addAttribute("fromEmail",
					ReadApplicationSettings.getApplicationProperty(ApplicationConstants.FROM_EMAIL));
		}
		return sendEmailModel;
	}

	@PostMapping("/backToTermSheet")
	public RedirectView backToTermSheet(@RequestParam("termSheetId") String termSheetId, HttpServletRequest request,
										RedirectAttributes redirectAttributes) {
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		RedirectView model = new RedirectView("manageTermSheet");
		redirectAttributes.addFlashAttribute("termSheetId", termSheetId);
		return model;
	}

	@PostMapping("/backToViewSummaryAfterEmailSent")
	public RedirectView backToViewSummaryAfterEmailSent(@RequestParam("termSheetId") String termSheetId,
														@RequestParam("emailSentStatus") String emailSentStatus, HttpServletRequest request,
														RedirectAttributes redirectAttributes) {
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		RedirectView model = new RedirectView("viewTermSheetSummary/" + termSheetId);
		redirectAttributes.addFlashAttribute("emailSentStatus", emailSentStatus);
		return model;
	}

	@ResponseBody
	@PostMapping("/sendLetterOfIntent")
	public String sendLOI(@RequestParam("attachment") MultipartFile file, @RequestParam("to") String to,
						  @RequestParam("termSheetId") String termSheetId, @RequestParam("professorId") String professorId,
						  @RequestParam("body") String body, @RequestParam("from") String from,
						  @RequestParam("subject") String subject, @RequestParam("bcc") String bcc, @RequestParam("cc") String cc) {
		Path path = Paths.get("");
		if (file != null && !file.getOriginalFilename().equals("")) {
			try {
				byte[] bytes = file.getBytes();
				path = Paths.get(file.getOriginalFilename());
				Files.write(path, bytes);
			} catch (Exception e) {
				return "false";
			}
		}
		EmailService emailService = new EmailService();
		String updatedBody = ApplicationConstants.LETTER_OF_INTENT_PREFIX + body
				+ ApplicationConstants.LETTER_OF_INTENT_POSTFIX;
		if (emailService.sendEmail(to, updatedBody, subject, cc, bcc, path.toString())) {
			if (!path.toString().equals(""))
				path.toFile().delete();
			termSheetRepository.modifyStatus(Long.parseLong(professorId), Long.parseLong(termSheetId),
					ProfessorStatus.NEW_HIRE.getStatus());
			return "true";
		} else {
			if (!path.toString().equals(""))
				path.toFile().delete();
			return "false";
		}
	}

	@ResponseBody
	@PostMapping("/sendPartTimeContractEmail")
	public String sendPartTimeContractEmail(@RequestParam("attachment") MultipartFile file,
											@RequestParam("to") String to, @RequestParam("termSheetId") String termSheetId,
											@RequestParam("professorId") String professorId, @RequestParam("body") String body,
											@RequestParam("from") String from, @RequestParam("subject") String subject, @RequestParam("bcc") String bcc,
											@RequestParam("cc") String cc) {
		Path path = Paths.get("");
		if (file != null && !file.getOriginalFilename().equals("")) {
			try {
				byte[] bytes = file.getBytes();
				path = Paths.get(file.getOriginalFilename());
				Files.write(path, bytes);
			} catch (Exception e) {
				return "false";
			}
		}
		EmailService emailService = new EmailService();
		String updatedBody = ApplicationConstants.PART_TIME_PARTIAL_LOAD_CONTRACT_PREFIX + body
				+ ApplicationConstants.PART_TIME_PARTIAL_LOAD_CONTRACT_POSTFIX;
		if (emailService.sendEmail(to, updatedBody, subject, cc, bcc, path.toString())) {
			if (!path.toString().equals(""))
				path.toFile().delete();
			termSheetRepository.modifyStatus(Long.parseLong(professorId), Long.parseLong(termSheetId),
					ProfessorStatus.CONTRACT_SENT_TO_FACULTY.getStatus());
			return "true";
		} else {
			if (!path.toString().equals(""))
				path.toFile().delete();
			return "false";
		}
	}

	@ResponseBody
	@PostMapping("/sendPartialLoadContractEmail")
	public String sendPartialLoadContractEmail(@RequestParam("attachment") MultipartFile file,
											   @RequestParam("to") String to, @RequestParam("termSheetId") String termSheetId,
											   @RequestParam("professorId") String professorId, @RequestParam("body") String body,
											   @RequestParam("from") String from, @RequestParam("subject") String subject, @RequestParam("bcc") String bcc,
											   @RequestParam("cc") String cc) {
		Path path = Paths.get("");
		if (file != null && !file.getOriginalFilename().equals("")) {
			try {
				byte[] bytes = file.getBytes();
				path = Paths.get(file.getOriginalFilename());
				Files.write(path, bytes);
			} catch (Exception e) {
				return "false";
			}
		}
		EmailService emailService = new EmailService();
		String updatedBody = ApplicationConstants.PART_TIME_PARTIAL_LOAD_CONTRACT_PREFIX + body
				+ ApplicationConstants.PART_TIME_PARTIAL_LOAD_CONTRACT_POSTFIX;
		if (emailService.sendEmail(to, updatedBody, subject, cc, bcc, path.toString())) {
			if (!path.toString().equals(""))
				path.toFile().delete();
			termSheetRepository.modifyStatus(Long.parseLong(professorId), Long.parseLong(termSheetId),
					ProfessorStatus.CONTRACT_SENT_TO_FACULTY.getStatus());
			return "true";
		} else {
			if (!path.toString().equals(""))
				path.toFile().delete();
			return "false";
		}
	}

	@ResponseBody
	@PostMapping("/sendTeachingNotification")
	public String sendTeachingNotification(@RequestParam("attachment") MultipartFile file,
										   @RequestParam("to") String to, @RequestParam("termSheetId") String termSheetId,
										   @RequestParam("professorId") String professorId, @RequestParam("body") String body,
										   @RequestParam("from") String from, @RequestParam("subject") String subject, @RequestParam("bcc") String bcc,
										   @RequestParam("cc") String cc) {
		Path path = Paths.get("");
		if (file != null && !file.getOriginalFilename().equals("")) {
			try {
				byte[] bytes = file.getBytes();
				path = Paths.get(file.getOriginalFilename());
				Files.write(path, bytes);
			} catch (Exception e) {
				return "false";
			}
		}
		EmailService emailService = new EmailService();
		String updatedBody = ApplicationConstants.TEACHING_NOTIFICATION_PREFIX + body
				+ ApplicationConstants.TEACHING_NOTIFICATION_POSTFIX;
		if (emailService.sendEmail(to, updatedBody, subject, cc, bcc, path.toString())) {
			if (!path.toString().equals(""))
				path.toFile().delete();
			termSheetRepository.modifyStatus(Long.parseLong(professorId), Long.parseLong(termSheetId),
					ProfessorStatus.TEACHING_NOTIFICATION.getStatus());
			return "true";
		} else {
			if (!path.toString().equals(""))
				path.toFile().delete();
			return "false";
		}
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/download/{termSheetId}")
	public ResponseEntity exportTermSheetInExcel(@PathVariable("termSheetId") Long termSheetId) throws IOException {
		List<TermSheet> termSheets = termSheetRepository.findByTermSheetId(termSheetId);
		ByteArrayInputStream in = excelExportService.exportFile(termSheets.get(0));
		// return IOUtils.toByteArray(in);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + termSheets.get(0).getTermSheetName() + ".xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}

}
