package com.fanshawe.nfttracker.api.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanshawe.nfttracker.api.dao.ProfessorDao;
import com.fanshawe.nfttracker.api.entities.Course;
import com.fanshawe.nfttracker.api.entities.Professor;
import com.fanshawe.nfttracker.api.repositories.CourseRepository;
import com.fanshawe.nfttracker.api.repositories.ProfessorRepository;
import com.fanshawe.nfttracker.api.request.CourseRequest;
import com.fanshawe.nfttracker.api.request.ProfessorRequest;
import com.fanshawe.nfttracker.api.response.ProfessorResponse;
import com.fanshawe.nfttracker.helper.ApplicationHelper;
import com.fanshawe.nfttracker.helper.CopyListProperties;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class ProfessorDaoImpl implements ProfessorDao {

	@Autowired
	private ProfessorRepository professorRepo;

	@Autowired
	private CourseRepository courseRepo;

	@Override
	public ProfessorResponse addProfessor(List<ProfessorRequest> professorRequests) throws Exception {
		List<Professor> professorEntities = new CopyListProperties(Professor.class).copy(professorRequests);
		List<Professor> professorsSavedEntities = (List<Professor>) professorRepo.saveAll(professorEntities);
		ProfessorResponse response = new ProfessorResponse();
		response.setProfessors(new ArrayList<>());
		if (professorsSavedEntities != null) {
			response.setStatus(Boolean.TRUE);
			response.setProfessors(new CopyListProperties(ProfessorRequest.class).copy(professorsSavedEntities));
			response.setMessage("Professor details saved successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response,
				"Some error occured while saving professor details");
		return response;

	}

	@Override
	public ProfessorResponse getProfessors() {
		List<Professor> professors = (List<Professor>) professorRepo.findAll();
		ProfessorResponse response = new ProfessorResponse();
		response.setProfessors(new ArrayList<>());
		if (professors != null && professors.size() > 0) {
			response.setStatus(Boolean.TRUE);
			response.setProfessors(new CopyListProperties(ProfessorRequest.class).copy(professors));
			response.setMessage("Professor details fetched successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response,
				"No professor details found in the database");
		return response;
	}

	@Override
	public ProfessorResponse getProfessor(String professorEmailId) {
		List<Professor> professors = (List<Professor>) professorRepo.findByEmailId(professorEmailId);
		ProfessorResponse response = new ProfessorResponse();
		response.setProfessors(new ArrayList<>());
		if (professors != null && professors.size() > 0) {
			response.setStatus(Boolean.TRUE);
			response.setProfessors(professors);
			response.setMessage("Professor details fetched successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response,
				"Professor details not found in the database for emailId:" + professorEmailId);
		return response;
	}

	@Override
	public ProfessorResponse updateProfessor(Long professorId, ProfessorRequest professor) {
		Optional<Professor> professorSaved = professorRepo.findById(professorId);
		ProfessorResponse response = new ProfessorResponse();
		response.setProfessors(new ArrayList<>());
		if (professorSaved.get() != null) {
			response.setStatus(Boolean.TRUE);
			BeanUtils.copyProperties(professor, professorSaved.get());
			Professor professorUpdated = professorRepo.save(professorSaved.get());
			response.setProfessors(Arrays.asList(professorUpdated));
			response.setMessage("Professor details updated successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response,
				"Professor details not found in the database");
		return response;
	}

	@Override
	public ProfessorResponse deleteProfessor(String professorEmailId) {
		List<Professor> professorSaved = professorRepo.findByEmailId(professorEmailId);
		ProfessorResponse response = new ProfessorResponse();
		response.setProfessors(new ArrayList<>());
		if (professorSaved != null && professorSaved.size() > 0) {
			response.setStatus(Boolean.TRUE);
			professorRepo.delete(professorSaved.get(0));
			List<Professor> deletedProfessorDetails = new ArrayList<Professor>();
			deletedProfessorDetails.add(professorSaved.get(0));
			response.setProfessors(new CopyListProperties(ProfessorRequest.class).copy(deletedProfessorDetails));
			response.setMessage("Professor deleted successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response,
				"Professor details not found in the database for emailId:" + professorEmailId);
		return response;

	}

	@Override
	public ProfessorResponse assignDeassignCoursesToProfessor(String professorEmailId,
			List<CourseRequest> courseRequests, String operation) {

		List<Professor> professorSaved = professorRepo.findByEmailId(professorEmailId);
		ProfessorResponse response = new ProfessorResponse();
		response.setProfessors(new ArrayList<>());
		if (professorSaved != null && professorSaved.size() > 0 && professorSaved.get(0) != null) {
			List<Long> courseIds = new ArrayList<Long>();
			for (int i = 0; i < courseRequests.size(); i++) {
				List<Course> courses = courseRepo.findByCourseCode(courseRequests.get(i).getCourseCode());
				if (courses != null && courses.size() > 0) {
					courseIds.add(courses.get(0).getCourseId());
					if (operation.equalsIgnoreCase("assign"))
						courseRepo.assignCoursetoProfessor(professorSaved.get(0).getProfessorId(),
								courses.get(0).getCourseId());
				} else {
					response.setMessage(
							"Please check the courses in the request body, some of them doesn't exists in database");
					return response;
				}
			}
			response.setStatus(Boolean.TRUE);
			if (operation.equalsIgnoreCase("deassign")) {
				courseRepo.deassignCourseFromProfessor(professorSaved.get(0).getProfessorId(), courseIds);
				response.setMessage("Courses deassigned from professor successfully");
			} else if (operation.equalsIgnoreCase("assign"))
				response.setMessage("Courses assigned to professor successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response,
				"Please check the professor details specified in the request");
		return response;

	}

}