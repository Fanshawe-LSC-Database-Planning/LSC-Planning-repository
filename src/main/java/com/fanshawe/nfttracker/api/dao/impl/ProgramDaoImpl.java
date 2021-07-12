package com.fanshawe.nfttracker.api.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanshawe.nfttracker.api.dao.ProgramDao;
import com.fanshawe.nfttracker.api.entities.Program;
import com.fanshawe.nfttracker.api.entities.ProgramLevel;
import com.fanshawe.nfttracker.api.repositories.ProgramLevelRepository;
import com.fanshawe.nfttracker.api.repositories.ProgramRepository;
import com.fanshawe.nfttracker.api.request.ProgramLevelRequest;
import com.fanshawe.nfttracker.api.request.ProgramRequest;
import com.fanshawe.nfttracker.api.response.ProgramResponse;
import com.fanshawe.nfttracker.helper.ApplicationHelper;
import com.fanshawe.nfttracker.helper.CopyListProperties;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class ProgramDaoImpl implements ProgramDao {

	@Autowired
	private ProgramRepository programRepo;

	@Autowired
	private ProgramLevelRepository programLevelRepo;

	@Override
	public ProgramResponse addProgram(List<ProgramRequest> programs) throws Exception {
		boolean flag = false;
		for (ProgramRequest program : programs) {
			List<Program> programsInDb = programRepo.findByProgramCode(program.getProgramCode());
			if (programsInDb != null && programsInDb.size() > 0) {
				flag = true;
				break;
			}
		}
		ProgramResponse response = new ProgramResponse();
		response.setPrograms(new ArrayList<>());
		if (!flag) {
			List<Program> programEntities = new CopyListProperties(Program.class).copy(programs);
			List<Program> programSavedEntities = (List<Program>) programRepo.saveAll(programEntities);

			if (programSavedEntities != null) {
				response.setStatus(Boolean.TRUE);
				response.setPrograms(programSavedEntities);
				response.setMessage("Program details saved successfully");
				return response;
			}
			ApplicationHelper.configureFailureResponseWithoutException(response,
					"Some error occured while saving program details");
			return response;
		} else {
			ApplicationHelper.configureFailureResponseWithoutException(response,
					"Program with program code already exists");
			return response;
		}
	}

	@Override
	public ProgramResponse getPrograms() {
		List<Program> programs = (List<Program>) programRepo.findAll();
		ProgramResponse response = new ProgramResponse();
		response.setPrograms(new ArrayList<>());
		if (programs != null && programs.size() > 0) {
			response.setPrograms(programs);
			response.setStatus(Boolean.TRUE);
			response.setMessage("Program details fetched successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response,
				"No program details found in the database");
		return response;
	}

	@Override
	public ProgramResponse getProgram(String programCode) {
		List<Program> programs = (List<Program>) programRepo.findByProgramCode(programCode);
		ProgramResponse response = new ProgramResponse();
		response.setPrograms(new ArrayList<>());
		if (programs != null && programs.size() > 0) {
			response.setPrograms(programs);
			response.setStatus(Boolean.TRUE);
			response.setMessage("Program details fetched successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response,
				"No program details found in the database for program code: " + programCode);
		return response;
	}

	@Override
	public ProgramResponse updateProgram(String programCode, ProgramRequest program) {
		List<Program> programSaved = programRepo.findByProgramCode(programCode);
		ProgramResponse response = new ProgramResponse();
		response.setPrograms(new ArrayList<>());

		if (programSaved != null && programSaved.size() > 0) {
			response.setStatus(Boolean.TRUE);
			BeanUtils.copyProperties(program, programSaved.get(0));
			Program programUpdated = programRepo.save(programSaved.get(0));
			response.setPrograms(Arrays.asList(programUpdated));
			response.setMessage("Program details updated successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response,
				"No program details found in the database for program code: " + programCode);
		return response;
	}

	@Override
	public ProgramResponse deleteProgram(String programCode) {
		List<Program> programSaved = programRepo.findByProgramCode(programCode);
		ProgramResponse response = new ProgramResponse();
		response.setPrograms(new ArrayList<>());

		if (programSaved != null && programSaved.size() > 0) {
			response.setStatus(Boolean.TRUE);
			programRepo.delete(programSaved.get(0));
			response.setPrograms(Arrays.asList(programSaved.get(0)));
			response.setMessage("Program deleted successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response,
				"No program details found in the database for program code: " + programCode);
		return response;
	}

	@Override
	public ProgramResponse addRemoveProgramLevelToProgram(String programCode,
			List<ProgramLevelRequest> programLevelRequests, String operation) {
		List<Program> programSaved = programRepo.findByProgramCode(programCode);
		ProgramResponse response = new ProgramResponse();
		response.setPrograms(new ArrayList<>());

		if (programSaved != null && programSaved.size() > 0 && programSaved.get(0) != null) {
			List<String> levelNames = new ArrayList<String>();
			for (int i = 0; i < programLevelRequests.size(); i++) {
				List<ProgramLevel> programLevel = programLevelRepo
						.findByLevelName(programLevelRequests.get(i).getLevelName());
				if (programLevel != null && programLevel.size() > 0) {
					levelNames.add(programLevel.get(0).getLevelName());
				} else {
					response.setMessage(
							"Please check the program level in the request body, some of them doesn't exists in database");
					return response;
				}
			}
			if (operation.equalsIgnoreCase("add"))
				programLevelRepo.addProgramLevelInProgram(programSaved.get(0).getProgramId(), levelNames);
			else
				programLevelRepo.removeProgramLevelFromProgram(levelNames);
			response.setStatus(Boolean.TRUE);
			if (operation.equalsIgnoreCase("add"))
				response.setMessage("Program levels added to program successfully");
			else
				response.setMessage("Program levels removed from program successfully");
			return response;

		}
		ApplicationHelper.configureFailureResponseWithoutException(response,
				"Please check the program name specified in the request");
		return response;
	}

}
