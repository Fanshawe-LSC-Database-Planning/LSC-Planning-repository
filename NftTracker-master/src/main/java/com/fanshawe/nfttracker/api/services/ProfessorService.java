package com.fanshawe.nfttracker.api.services;

import java.util.List;

import com.fanshawe.nfttracker.api.request.CourseRequest;
import com.fanshawe.nfttracker.api.request.ProfessorRequest;
import com.fanshawe.nfttracker.api.response.ProfessorResponse;

/**
 * @author Raman.Ahuja
 * 
 *         Professor Entity Service Interface for database CRUD layer
 *         interaction
 *
 */

public interface ProfessorService {

	public ProfessorResponse addProfessor(List<ProfessorRequest> professor);

	public ProfessorResponse getProfessors();

	public ProfessorResponse getProfessor(String professorEmailId);

	public ProfessorResponse updateProfessor(Long professorId, ProfessorRequest professor);

	public ProfessorResponse deleteProfessor(String professorEmailId);

	public ProfessorResponse assignCourseToProfessor(String professorEmailId, List<CourseRequest> courseRequests);

	public ProfessorResponse deassignCourseFromProfessor(String professorEmailId, List<CourseRequest> courseRequests);

}
