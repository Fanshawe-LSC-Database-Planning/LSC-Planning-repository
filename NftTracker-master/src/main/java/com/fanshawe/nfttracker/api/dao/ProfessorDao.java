package com.fanshawe.nfttracker.api.dao;

import java.util.List;

import com.fanshawe.nfttracker.api.request.CourseRequest;
import com.fanshawe.nfttracker.api.request.ProfessorRequest;
import com.fanshawe.nfttracker.api.response.ProfessorResponse;

public interface ProfessorDao {

	public ProfessorResponse addProfessor(List<ProfessorRequest> professor) throws Exception;

	public ProfessorResponse getProfessors();

	public ProfessorResponse getProfessor(String professorEmailId);

	public ProfessorResponse updateProfessor(Long professorId, ProfessorRequest professor);

	public ProfessorResponse deleteProfessor(String professorEmailId);

	public ProfessorResponse assignDeassignCoursesToProfessor(String professorEmailId,
			List<CourseRequest> courseRequests, String operation);
}
