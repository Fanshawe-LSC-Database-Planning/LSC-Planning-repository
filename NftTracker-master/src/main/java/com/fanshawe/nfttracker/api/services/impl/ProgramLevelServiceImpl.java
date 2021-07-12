package com.fanshawe.nfttracker.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanshawe.nfttracker.api.dao.ProgramLevelDao;
import com.fanshawe.nfttracker.api.request.CourseRequest;
import com.fanshawe.nfttracker.api.request.ProgramLevelRequest;
import com.fanshawe.nfttracker.api.response.ProgramLevelResponse;
import com.fanshawe.nfttracker.api.services.ProgramLevelService;
import com.fanshawe.nfttracker.helper.ApplicationHelper;

@Service
public class ProgramLevelServiceImpl implements ProgramLevelService {

	@Autowired
	ProgramLevelDao programLevelDao;

	@Override
	public ProgramLevelResponse addProgramLevel(List<ProgramLevelRequest> programLevels) {
		ProgramLevelResponse response = new ProgramLevelResponse();
		try {
			response = programLevelDao.addProgramLevel(programLevels);
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public ProgramLevelResponse getProgramLevels() {
		ProgramLevelResponse response = new ProgramLevelResponse();
		try {
			response = programLevelDao.getProgramLevels();
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public ProgramLevelResponse getProgramLevel(String programLevelName) {
		ProgramLevelResponse response = new ProgramLevelResponse();
		try {
			response = programLevelDao.getProgramLevel(programLevelName);
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public ProgramLevelResponse updateProgramLevel(String programLevelName, ProgramLevelRequest programLevel) {
		ProgramLevelResponse response = new ProgramLevelResponse();
		try {
			response = programLevelDao.updateProgramLevel(programLevelName, programLevel);
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public ProgramLevelResponse deleteProgramLevel(String programLevelName) {
		ProgramLevelResponse response = new ProgramLevelResponse();
		try {
			response = programLevelDao.deleteProgramLevel(programLevelName);
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public ProgramLevelResponse addCourseToProgramLevel(String programLevelName, List<CourseRequest> courseRequests) {
		ProgramLevelResponse response = new ProgramLevelResponse();
		try {
			response = programLevelDao.addRemoveCoursesToProgramLevel(programLevelName, courseRequests, "add");
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public ProgramLevelResponse removeCourseFromProgramLevel(String programLevelName,
			List<CourseRequest> courseRequests) {
		ProgramLevelResponse response = new ProgramLevelResponse();
		try {
			response = programLevelDao.addRemoveCoursesToProgramLevel(programLevelName, courseRequests, "remove");
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

}
