package com.fanshawe.nfttracker.helper.pojo;

public class ProgramToCourses {
	private String programName;
	private String programLevelName;
	private String courseCode;
	private String courseName;

	public ProgramToCourses(String programName, String programLevelName, String courseCode, String courseName) {
		super();
		this.programName = programName;
		this.programLevelName = programLevelName;
		this.courseCode = courseCode;
		this.courseName = courseName;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProgramLevelName() {
		return programLevelName;
	}

	public void setProgramLevelName(String programLevelName) {
		this.programLevelName = programLevelName;
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

}
