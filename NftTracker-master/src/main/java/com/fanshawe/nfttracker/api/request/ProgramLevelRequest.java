package com.fanshawe.nfttracker.api.request;

import java.util.List;

import com.fanshawe.nfttracker.api.entities.Course;

public class ProgramLevelRequest {

	String levelName;

	List<Course> courses;

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	@Override
	public String toString() {
		return "ProgramLevel [levelName=" + levelName + ", courses=" + courses + "]";
	}

}
