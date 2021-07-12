package com.fanshawe.nfttracker.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanshawe.nfttracker.api.dao.CourseDao;
import com.fanshawe.nfttracker.api.request.CourseRequest;
import com.fanshawe.nfttracker.api.response.CourseResponse;
import com.fanshawe.nfttracker.api.services.CourseService;
import com.fanshawe.nfttracker.helper.ApplicationHelper;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseDao courseDao;

	public CourseResponse addCourses(List<CourseRequest> courseRequests) {
		CourseResponse response = new CourseResponse();
		try {
			response = courseDao.addCourse(courseRequests);
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public CourseResponse getCourses() {
		CourseResponse response = new CourseResponse();
		try {
			response = courseDao.getCourses();
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;

	}

	@Override
	public CourseResponse getCourse(String courseName) {
		CourseResponse response = new CourseResponse();
		try {
			response = courseDao.getCourse(courseName);
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public CourseResponse updateCourse(String courseName, CourseRequest course) {
		CourseResponse response = new CourseResponse();
		try {
			response = courseDao.updateCourse(courseName, course);
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public CourseResponse deleteCourse(String courseName) {
		CourseResponse response = new CourseResponse();
		try {
			response = courseDao.deleteCourse(courseName);
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

}
