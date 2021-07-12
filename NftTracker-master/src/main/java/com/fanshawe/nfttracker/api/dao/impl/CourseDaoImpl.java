package com.fanshawe.nfttracker.api.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanshawe.nfttracker.api.dao.CourseDao;
import com.fanshawe.nfttracker.api.entities.Course;
import com.fanshawe.nfttracker.api.repositories.CourseRepository;
import com.fanshawe.nfttracker.api.request.CourseRequest;
import com.fanshawe.nfttracker.api.response.CourseResponse;
import com.fanshawe.nfttracker.helper.ApplicationHelper;
import com.fanshawe.nfttracker.helper.CopyListProperties;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class CourseDaoImpl implements CourseDao {
	@Autowired
	private CourseRepository courseRepo;

	@Override
	public CourseResponse addCourse(List<CourseRequest> courseRequests) throws Exception {
		List<Course> courseEntities = new CopyListProperties(Course.class).copy(courseRequests);
		List<Course> courseSavedEntities = (List<Course>) courseRepo.saveAll(courseEntities);
		CourseResponse response = new CourseResponse();
		response.setCourses(new ArrayList<>());
		if (courseSavedEntities != null) {
			response.setCourses(new CopyListProperties(CourseRequest.class).copy(courseSavedEntities));
			response.setMessage("Course details saved successfully");
			response.setStatus(Boolean.TRUE);
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response,
				"Some error occured while saving Course details");
		return response;
	}

	@Override
	public CourseResponse getCourses() {
		List<Course> courses = (List<Course>) courseRepo.findAll();
		CourseResponse response = new CourseResponse();
		if (courses != null && courses.size() > 0) {
			response.setCourses(new CopyListProperties(Course.class).copy(courses));
			response.setStatus(Boolean.TRUE);
			response.setMessage("details fetched successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response, "No course details found in the database");
		return response;

	}

	@Override
	public CourseResponse getCourse(String courseCode) {
		List<Course> courses = (List<Course>) courseRepo.findCourseByCourseName(courseCode);
		CourseResponse response = new CourseResponse();
		if (courses != null && courses.size() > 0) {
			response.setCourses(new CopyListProperties(Course.class).copy(courses));
			response.setStatus(Boolean.TRUE);
			response.setMessage("Course details fetched successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response,
				"Course details not found in the database for Course Name:" + courseCode);
		return response;
	}

	@Override
	public CourseResponse updateCourse(String courseCode, CourseRequest course) {
		List<Course> courseSaved = courseRepo.findByCourseCode(courseCode);
		CourseResponse response = new CourseResponse();
		if (courseSaved != null && courseSaved.size() > 0) {
			BeanUtils.copyProperties(course, courseSaved.get(0));
			Course courseUpdated = courseRepo.save(courseSaved.get(0));
			List<Course> updatedCourseDetails = new ArrayList<Course>();
			updatedCourseDetails.add(courseUpdated);
			response.setCourses(new CopyListProperties(Course.class).copy(updatedCourseDetails));
			response.setMessage("Course details updated successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response,
				"Course details not found in the database for Course Name:" + courseCode);
		return response;
	}

	@Override
	public CourseResponse deleteCourse(String courseCode) {
		List<Course> courseSaved = courseRepo.findByCourseCode(courseCode);
		CourseResponse response = new CourseResponse();
		if (courseSaved != null && courseSaved.size() > 0) {
			courseRepo.delete(courseSaved.get(0));
			List<Course> deletedCourseDetails = new ArrayList<Course>();
			deletedCourseDetails.add(courseSaved.get(0));
			response.setCourses(new CopyListProperties(Course.class).copy(deletedCourseDetails));
			response.setMessage("Course deleted successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response,
				"Course details not found in the database for Course Name:" + courseCode);
		return response;
	}

}
