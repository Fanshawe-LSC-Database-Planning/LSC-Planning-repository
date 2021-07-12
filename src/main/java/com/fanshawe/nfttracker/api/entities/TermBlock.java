package com.fanshawe.nfttracker.api.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class TermBlock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long termBlockId;

	String programName;

	String levelName;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "termBlockId")
	@OrderBy("courseName")
	List<CourseInfo> listOfCourses;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	Date termStartDate;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	Date termEndDate;

	Long totalWeeks;

	public Long getTermBlockId() {
		return termBlockId;
	}

	public void setTermBlockId(Long termBlockId) {
		this.termBlockId = termBlockId;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public List<CourseInfo> getListOfCourses() {
		return listOfCourses;
	}

	public void setListOfCourses(List<CourseInfo> listOfCourses) {
		this.listOfCourses = listOfCourses;
	}

	public Date getTermStartDate() {
		return termStartDate;
	}

	public void setTermStartDate(Date termStartDate) {
		this.termStartDate = termStartDate;
	}

	public Date getTermEndDate() {
		return termEndDate;
	}

	public void setTermEndDate(Date termEndDate) {
		this.termEndDate = termEndDate;
	}

	public Long getTotalWeeks() {
		return totalWeeks;
	}

	public void setTotalWeeks(Long totalWeeks) {
		this.totalWeeks = totalWeeks;
	}

	@Override
	public String toString() {
		return "TermBlock [termBlockId=" + termBlockId + ", programName=" + programName + ", levelName=" + levelName
				+ ", listOfCourses=" + listOfCourses + ", termStartDate=" + termStartDate + ", termEndDate="
				+ termEndDate + ", totalWeeks=" + totalWeeks + "]";
	}

}
