package com.fanshawe.nfttracker.api.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Embeddable
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	Long courseId;

	@Column(nullable = false)
	String courseCode;

	@Column(nullable = false)
	String courseName;

	@Column(nullable = false)
	Long courseCredit;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "suggestedCourses")
	List<Professor> suggestedProfessors;

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Long getCourseCredit() {
		return courseCredit;
	}

	public void setCourseCredit(Long courseCredit) {
		this.courseCredit = courseCredit;
	}

	public List<Professor> getSuggestedProfessors() {
		return suggestedProfessors;
	}

	public void setSuggestedProfessors(List<Professor> suggestedProfessors) {
		this.suggestedProfessors = suggestedProfessors;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseCode=" + courseCode + ", courseName=" + courseName
				+ ", courseCredit=" + courseCredit + ", suggestedProfessors=" + suggestedProfessors + "]";
	}

}
