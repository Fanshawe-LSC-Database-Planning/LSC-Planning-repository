package com.fanshawe.nfttracker.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanshawe.nfttracker.api.dao.ProgramDao;
import com.fanshawe.nfttracker.api.request.ProgramLevelRequest;
import com.fanshawe.nfttracker.api.request.ProgramRequest;
import com.fanshawe.nfttracker.api.response.ProgramResponse;
import com.fanshawe.nfttracker.api.services.ProgramService;
import com.fanshawe.nfttracker.helper.ApplicationHelper;

@Service
public class ProgramServiceImpl implements ProgramService {

	@Autowired
	private ProgramDao programDao;

	@Override
	public ProgramResponse addProgram(List<ProgramRequest> programs) {
		ProgramResponse response = new ProgramResponse();
		try {
			response = programDao.addProgram(programs);
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public ProgramResponse getPrograms() {
		ProgramResponse response = new ProgramResponse();
		try {
			response = programDao.getPrograms();
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public ProgramResponse getProgram(String programCode) {
		ProgramResponse response = new ProgramResponse();
		try {
			response = programDao.getProgram(programCode);
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public ProgramResponse updateProgram(String programCode, ProgramRequest program) {
		ProgramResponse response = new ProgramResponse();
		try {
			response = programDao.updateProgram(programCode, program);
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public ProgramResponse deleteProgram(String programCode) {
		ProgramResponse response = new ProgramResponse();
		try {
			response = programDao.deleteProgram(programCode);
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public ProgramResponse addProgramLevelToProgram(String programLevelName, List<ProgramLevelRequest> programLevels) {
		ProgramResponse response = new ProgramResponse();
		try {
			response = programDao.addRemoveProgramLevelToProgram(programLevelName, programLevels, "add");
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public ProgramResponse removeProgramLevelToProgram(String programLevelName,
			List<ProgramLevelRequest> programLevelRequests) {
		ProgramResponse response = new ProgramResponse();
		try {
			response = programDao.addRemoveProgramLevelToProgram(programLevelName, programLevelRequests, "remove");
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

}
