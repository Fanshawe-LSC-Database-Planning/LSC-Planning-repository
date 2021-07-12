package com.fanshawe.nfttracker.api.services;

import java.util.List;

import com.fanshawe.nfttracker.api.request.CourseRequest;
import com.fanshawe.nfttracker.api.request.ProgramLevelRequest;
import com.fanshawe.nfttracker.api.response.ProgramLevelResponse;

/**
 * @author Raman.Ahuja
 * 
 *         Professor Entity Service Interface for database CRUD layer
 *         interaction
 *
 */

public interface ProgramLevelService {
	public ProgramLevelResponse addProgramLevel(List<ProgramLevelRequest> programLevels);

	public ProgramLevelResponse getProgramLevels();

	public ProgramLevelResponse getProgramLevel(String programLevelName);

	public ProgramLevelResponse updateProgramLevel(String programLevelName, ProgramLevelRequest programLevel);

	public ProgramLevelResponse deleteProgramLevel(String programLevelName);

	public ProgramLevelResponse addCourseToProgramLevel(String programLevelName, List<CourseRequest> courseRequests);

	public ProgramLevelResponse removeCourseFromProgramLevel(String programLevelName,
			List<CourseRequest> courseRequests);

}
