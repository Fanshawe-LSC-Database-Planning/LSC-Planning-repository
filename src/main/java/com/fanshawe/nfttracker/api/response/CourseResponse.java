package com.fanshawe.nfttracker.api.response;

import java.util.List;

import com.fanshawe.nfttracker.api.entities.Course;

public class CourseResponse extends ApiResponse {
	private List<Course> courses;

	@Override
	public String toString() {
		return "CourseResponse [Courses=" + courses + "]";
	}

	public List<Course> getCourse() {
		return courses;
	}

	public void setCourses(List<Course> arrayList) {
		this.courses = arrayList;
	}
}
