package com.fanshawe.nfttracker.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanshawe.nfttracker.api.dao.ProfessorDao;
import com.fanshawe.nfttracker.api.request.CourseRequest;
import com.fanshawe.nfttracker.api.request.ProfessorRequest;
import com.fanshawe.nfttracker.api.response.ProfessorResponse;
import com.fanshawe.nfttracker.api.services.ProfessorService;
import com.fanshawe.nfttracker.helper.ApplicationHelper;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
	ProfessorDao professorDao;

	@Override
	public ProfessorResponse addProfessor(List<ProfessorRequest> professorRequests) {
		ProfessorResponse response = new ProfessorResponse();
		try {
			response = professorDao.addProfessor(professorRequests);
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public ProfessorResponse getProfessors() {
		ProfessorResponse response = new ProfessorResponse();
		try {
			response = professorDao.getProfessors();
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public ProfessorResponse getProfessor(String professorEmailId) {
		ProfessorResponse response = new ProfessorResponse();
		try {
			response = professorDao.getProfessor(professorEmailId);
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public ProfessorResponse updateProfessor(Long professorId, ProfessorRequest professor) {
		ProfessorResponse response = new ProfessorResponse();
		try {
			response = professorDao.updateProfessor(professorId, professor);
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public ProfessorResponse deleteProfessor(String professorEmailId) {
		ProfessorResponse response = new ProfessorResponse();
		try {
			response = professorDao.deleteProfessor(professorEmailId);
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public ProfessorResponse assignCourseToProfessor(String professorEmailId, List<CourseRequest> courseRequests) {
		ProfessorResponse response = new ProfessorResponse();
		try {
			response = professorDao.assignDeassignCoursesToProfessor(professorEmailId, courseRequests, "assign");
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public ProfessorResponse deassignCourseFromProfessor(String professorEmailId, List<CourseRequest> courseRequests) {
		ProfessorResponse response = new ProfessorResponse();
		try {
			response = professorDao.assignDeassignCoursesToProfessor(professorEmailId, courseRequests, "deassign");
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

}
