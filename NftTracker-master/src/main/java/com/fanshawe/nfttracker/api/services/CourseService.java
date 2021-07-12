package com.fanshawe.nfttracker.api.services;

import java.util.List;

import com.fanshawe.nfttracker.api.request.CourseRequest;
import com.fanshawe.nfttracker.api.response.CourseResponse;

public interface CourseService {

	public CourseResponse addCourses(List<CourseRequest> course);

	public CourseResponse getCourses();

	public CourseResponse getCourse(String courseName);

	public CourseResponse updateCourse(String courseName, CourseRequest course);

	public CourseResponse deleteCourse(String courseName);

}
