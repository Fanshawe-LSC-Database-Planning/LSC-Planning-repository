package com.fanshawe.nfttracker.api.request;

import java.util.ArrayList;
import java.util.List;

import com.fanshawe.nfttracker.api.entities.Professor;

public class CourseRequest {
	private Long courseId;

	private String courseCode;

	private String courseName;

	private Long courseCredit;

	private List<Professor> suggestedProfessor = new ArrayList<Professor>();

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) throws Exception {
		// if (courseCode != null && !courseCode.isEmpty())
		this.courseCode = courseCode;
		/*
		 * else throw new Exception("Course Code cannot be empty");
		 */
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) throws Exception {
		if (courseName != null && !courseName.isEmpty())
			this.courseName = courseName;
		else
			throw new Exception("Course Name cannot be empty");
	}

	public Long getCourseCredit() {
		return courseCredit;
	}

	public void setCourseCredit(Long courseCredit) throws Exception {
		if (courseCredit != null && courseCredit != 0)
			this.courseCredit = courseCredit;
		else
			throw new Exception("Course Credit cannot be 0 or empty");
	}

	public List<Professor> getSuggestedProfessors() {
		return suggestedProfessor;
	}

	public void setSuggestedProfessors(List<Professor> suggestedProfessors) {

		this.suggestedProfessor = suggestedProfessors;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseCode=" + courseCode + ", courseName=" + courseName
				+ ", courseCredit=" + courseCredit + ", suggestedProfessors=" + suggestedProfessor + "]";
	}

}
