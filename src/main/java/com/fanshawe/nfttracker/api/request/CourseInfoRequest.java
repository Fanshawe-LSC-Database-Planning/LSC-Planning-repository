package com.fanshawe.nfttracker.api.request;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class CourseInfoRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long courseInfoId;

	String courseName;

	String courseCode;

	Long courseCredit;

	String programBlock;

	String sectionName;

	Long numberOfStudents;
	String scheduledRequest;
	String status;

	@ElementCollection
	@CollectionTable(name = "assigned_professors", joinColumns = @JoinColumn(name = "courseInfoId"))
	@Column(name = "assinged_professors")
	List<String> assignedProfessors;

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

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CourseInfo [courseInfoId=" + courseInfoId + ", courseName=" + courseName + ", courseCode=" + courseCode
				+ ", courseCredit=" + courseCredit + ", programBlock=" + programBlock + ", sectionName=" + sectionName
				+ ", numberOfStudents=" + numberOfStudents + ", scheduledRequest=" + scheduledRequest + ", status="
				+ status + ", assignedProfessors=" + assignedProfessors + "]";
	}

	public List<String> getAssignedProfessors() {
		return assignedProfessors;
	}

	public void setAssignedProfessors(List<String> assignedProfessors) {
		this.assignedProfessors = assignedProfessors;
	}

}
