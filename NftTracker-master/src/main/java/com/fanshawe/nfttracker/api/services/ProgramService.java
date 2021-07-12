package com.fanshawe.nfttracker.api.services;

import java.util.List;

import com.fanshawe.nfttracker.api.request.ProgramLevelRequest;
import com.fanshawe.nfttracker.api.request.ProgramRequest;
import com.fanshawe.nfttracker.api.response.ProgramResponse;

/**
 * @author Raman.Ahuja
 * 
 *         Professor Entity Service Interface for database CRUD layer
 *         interaction
 *
 */

public interface ProgramService {
	public ProgramResponse addProgram(List<ProgramRequest> programs);

	public ProgramResponse getPrograms();

	public ProgramResponse getProgram(String programCode);

	public ProgramResponse updateProgram(String programCode, ProgramRequest program);

	public ProgramResponse deleteProgram(String programCode);

	public ProgramResponse addProgramLevelToProgram(String programCode, List<ProgramLevelRequest> programLevels);

	public ProgramResponse removeProgramLevelToProgram(String programCode,
			List<ProgramLevelRequest> programLevelRequests);
}
