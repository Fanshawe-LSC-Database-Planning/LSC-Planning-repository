package com.fanshawe.nfttracker.api.dao;

import java.util.List;

import com.fanshawe.nfttracker.api.request.CourseRequest;
import com.fanshawe.nfttracker.api.request.ProgramLevelRequest;
import com.fanshawe.nfttracker.api.response.ProgramLevelResponse;

public interface ProgramLevelDao {

	public ProgramLevelResponse addProgramLevel(List<ProgramLevelRequest> programLevels) throws Exception;

	public ProgramLevelResponse getProgramLevels();

	public ProgramLevelResponse getProgramLevel(String programLevelName);

	public ProgramLevelResponse updateProgramLevel(String programLevelName, ProgramLevelRequest programLevel);

	public ProgramLevelResponse deleteProgramLevel(String programLevelName);

	public ProgramLevelResponse addRemoveCoursesToProgramLevel(String programLevelName,
			List<CourseRequest> courseRequests, String operation);

}
