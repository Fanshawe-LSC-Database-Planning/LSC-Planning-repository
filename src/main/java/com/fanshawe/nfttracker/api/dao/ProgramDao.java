package com.fanshawe.nfttracker.api.dao;

import java.util.List;

import com.fanshawe.nfttracker.api.request.ProgramLevelRequest;
import com.fanshawe.nfttracker.api.request.ProgramRequest;
import com.fanshawe.nfttracker.api.response.ProgramResponse;

public interface ProgramDao {

	public ProgramResponse addProgram(List<ProgramRequest> programs) throws Exception;

	public ProgramResponse getPrograms();

	public ProgramResponse getProgram(String programCode);

	public ProgramResponse updateProgram(String programCode, ProgramRequest program);

	public ProgramResponse deleteProgram(String programCode);

	public ProgramResponse addRemoveProgramLevelToProgram(String programCode,
			List<ProgramLevelRequest> programLevelRequests, String operation);

}
