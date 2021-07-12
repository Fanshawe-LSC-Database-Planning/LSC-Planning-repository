package com.fanshawe.nfttracker.api.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanshawe.nfttracker.api.dao.ProgramLevelDao;
import com.fanshawe.nfttracker.api.entities.Course;
import com.fanshawe.nfttracker.api.entities.ProgramLevel;
import com.fanshawe.nfttracker.api.repositories.CourseRepository;
import com.fanshawe.nfttracker.api.repositories.ProgramLevelRepository;
import com.fanshawe.nfttracker.api.request.CourseRequest;
import com.fanshawe.nfttracker.api.request.ProgramLevelRequest;
import com.fanshawe.nfttracker.api.response.ProgramLevelResponse;
import com.fanshawe.nfttracker.helper.ApplicationHelper;
import com.fanshawe.nfttracker.helper.CopyListProperties;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class ProgramLevelDaoImpl implements ProgramLevelDao {

	@Autowired
	private ProgramLevelRepository programLevelRepo;

	@Autowired
	CourseRepository courseRepo;

	@Override
	public ProgramLevelResponse addProgramLevel(List<ProgramLevelRequest> programLevels) throws Exception {
		List<ProgramLevel> programLevelEntities = new CopyListProperties(ProgramLevel.class).copy(programLevels);
		List<ProgramLevel> programLevelSavedEntities = (List<ProgramLevel>) programLevelRepo
				.saveAll(programLevelEntities);
		ProgramLevelResponse response = new ProgramLevelResponse();
		response.setProgramLevels(new ArrayList<>());
		if (programLevelSavedEntities != null) {
			response.setStatus(Boolean.TRUE);
			response.setProgramLevels(programLevelSavedEntities);
			response.setMessage("Program level details saved successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response,
				"Some error occured while saving program level details");
		return response;

	}

	@Override
	public ProgramLevelResponse getProgramLevels() {
		List<ProgramLevel> programLevels = (List<ProgramLevel>) programLevelRepo.findAll();
		ProgramLevelResponse response = new ProgramLevelResponse();
		response.setProgramLevels(new ArrayList<>());
		if (programLevels != null && programLevels.size() > 0) {
			response.setStatus(Boolean.TRUE);
			response.setProgramLevels(programLevels);
			response.setMessage("Program level details fetched successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response,
				"No program level details found in the database");
		return response;
	}

	@Override
	public ProgramLevelResponse getProgramLevel(String programLevelName) {
		List<ProgramLevel> programLevels = (List<ProgramLevel>) programLevelRepo.findByLevelName(programLevelName);
		ProgramLevelResponse response = new ProgramLevelResponse();
		response.setProgramLevels(new ArrayList<>());
		if (programLevels != null && programLevels.size() > 0) {
			response.setStatus(Boolean.TRUE);
			response.setProgramLevels(programLevels);
			response.setMessage("Program level details fetched successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response,
				"No program level details found in the database for level name: " + programLevelName);
		return response;
	}

	@Override
	public ProgramLevelResponse updateProgramLevel(String programLevelName, ProgramLevelRequest programLevel) {
		List<ProgramLevel> programLevelSaved = programLevelRepo.findByLevelName(programLevelName);
		ProgramLevelResponse response = new ProgramLevelResponse();
		response.setProgramLevels(new ArrayList<>());
		if (programLevelSaved != null && programLevelSaved.size() > 0) {
			response.setStatus(Boolean.TRUE);
			BeanUtils.copyProperties(programLevel, programLevelSaved.get(0));
			ProgramLevel programLevelUpdated = programLevelRepo.save(programLevelSaved.get(0));
			response.setProgramLevels(Arrays.asList(programLevelUpdated));
			response.setMessage("Program level details updated successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response,
				"No program level details found in the database for level name: " + programLevelName);
		return response;
	}

	@Override
	public ProgramLevelResponse deleteProgramLevel(String programLevelName) {
		List<ProgramLevel> programLevelSaved = programLevelRepo.findByLevelName(programLevelName);
		ProgramLevelResponse response = new ProgramLevelResponse();
		response.setProgramLevels(new ArrayList<>());
		if (programLevelSaved != null && programLevelSaved.size() > 0) {
			response.setStatus(Boolean.TRUE);
			programLevelRepo.delete(programLevelSaved.get(0));
			response.setProgramLevels(Arrays.asList(programLevelSaved.get(0)));
			response.setMessage("Program level deleted successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response,
				"No program level details found in the database for level name: " + programLevelName);
		return response;

	}

	@Override
	public ProgramLevelResponse addRemoveCoursesToProgramLevel(String programLevelName,
			List<CourseRequest> courseRequests, String operation) {
		List<ProgramLevel> programLevelSaved = programLevelRepo.findByLevelName(programLevelName);
		ProgramLevelResponse response = new ProgramLevelResponse();
		response.setProgramLevels(new ArrayList<>());
		if (programLevelSaved != null && programLevelSaved.size() > 0 && programLevelSaved.get(0) != null) {
			List<String> courseCodes = new ArrayList<String>();
			for (int i = 0; i < courseRequests.size(); i++) {
				List<Course> courses = courseRepo.findByCourseCode(courseRequests.get(i).getCourseCode());
				if (courses != null && courses.size() > 0) {
					courseCodes.add(courses.get(0).getCourseCode());
				} else {
					response.setMessage(
							"Please check the courses in the request body, some of them doesn't exists in database");
					return response;
				}
			}

			if (operation.equalsIgnoreCase("add"))
				courseRepo.addCoursesInProgramLevel(programLevelSaved.get(0).getProgramLevelId(), courseCodes);
			else
				courseRepo.removeCoursesFromProgramLevel(courseCodes);
			response.setStatus(Boolean.TRUE);
			if (operation.equalsIgnoreCase("add"))
				response.setMessage("Courses added to program level successfully");
			else
				response.setMessage("Courses removed from program level successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response,
				"Please check the program level name specified in the request");
		return response;
	}

}
