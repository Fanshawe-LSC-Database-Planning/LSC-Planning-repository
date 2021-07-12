package com.fanshawe.nfttracker.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fanshawe.nfttracker.api.request.CourseRequest;
import com.fanshawe.nfttracker.api.response.CourseResponse;
import com.fanshawe.nfttracker.api.services.CourseService;

@RestController
@RequestMapping("/api/v1/courses")

public class CourseController {

	@Autowired
	CourseService courseService;

	@PostMapping
	public CourseResponse addCourse(@RequestBody List<CourseRequest> courseRequests) {
		CourseResponse response = null;
		try {
			response = courseService.addCourses(courseRequests);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(Boolean.FALSE);
		}
		return response;
	}

	@GetMapping
	public CourseResponse getAllCourses() {
		return courseService.getCourses();
	}

	@GetMapping("/{courseName}")
	public CourseResponse getCourseByCourseName(@PathVariable String courseName) {
		return courseService.getCourse(courseName);
	}

	@PutMapping("/{courseName}")
	public CourseResponse updateCourseByCourseName(@PathVariable String courseName,
			@RequestBody CourseRequest courseRequest) {
		return courseService.updateCourse(courseName, courseRequest);
	}

	@DeleteMapping("/{courseName}")
	public CourseResponse deleteCourseByCourseName(@PathVariable String courseName) {
		return courseService.deleteCourse(courseName);
	}
}
