package com.fanshawe.nfttracker.api.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;

@Entity
public class CourseInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long courseInfoId;

	String courseName;

	String courseCode;

	Long courseCredit;

	String programBlock;

	Long numberOfStudents;
	String scheduledRequest;

	@ElementCollection
	@MapKeyColumn(name = "professorId")
	@Column(name = "status")
	@CollectionTable(name = "professor_status", joinColumns = @JoinColumn(name = "courseInfoId"))
	Map<Long, String> professorStatus = new HashMap<Long, String>();

	String sectionName;

	@ElementCollection
	@CollectionTable(name = "assigned_professors", joinColumns = @JoinColumn(name = "courseInfoId"))
	@Column(name = "assinged_professors")
	List<Long> assignedProfessors;

	public Long getCourseInfoId() {
		return courseInfoId;
	}

	public void setCourseInfoId(Long courseInfoId) {
		this.courseInfoId = courseInfoId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public Long getCourseCredit() {
		return courseCredit;
	}

	public void setCourseCredit(Long courseCredit) {
		this.courseCredit = courseCredit;
	}

	public String getProgramBlock() {
		return programBlock;
	}

	public void setProgramBlock(String programBlock) {
		this.programBlock = programBlock;
	}

	public Long getNumberOfStudents() {
		return numberOfStudents;
	}

	public void setNumberOfStudents(Long numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}

	public String getScheduledRequest() {
		return scheduledRequest;
	}

	public void setScheduledRequest(String scheduledRequest) {
		this.scheduledRequest = scheduledRequest;
	}

	public List<Long> getAssignedProfessors() {
		return assignedProfessors;
	}

	public void setAssignedProfessors(List<Long> assignedProfessors) {
		this.assignedProfessors = assignedProfessors;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public Map<Long, String> getProfessorStatus() {
		return professorStatus;
	}

	public void setProfessorStatus(Map<Long, String> professorStatus) {
		this.professorStatus = professorStatus;
	}

	@Override
	public String toString() {
		return "CourseInfo [courseInfoId=" + courseInfoId + ", courseName=" + courseName + ", courseCode=" + courseCode
				+ ", courseCredit=" + courseCredit + ", programBlock=" + programBlock + ", numberOfStudents="
				+ numberOfStudents + ", scheduledRequest=" + scheduledRequest + ", professorStatus=" + professorStatus
				+ ", sectionName=" + sectionName + ", assignedProfessors=" + assignedProfessors + "]";
	}

}
