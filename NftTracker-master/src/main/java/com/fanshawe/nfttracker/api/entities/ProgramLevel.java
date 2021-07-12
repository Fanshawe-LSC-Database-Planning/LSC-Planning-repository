package com.fanshawe.nfttracker.api.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProgramLevel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	Long programLevelId;

	@Column(nullable = false, unique = true)
	String levelName;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "programLevelId")
	@Fetch(value = FetchMode.SUBSELECT)
	List<Course> courses = new ArrayList<Course>();

	public Long getProgramLevelId() {
		return programLevelId;
	}

	public void setProgramLevelId(Long programLevelId) {
		this.programLevelId = programLevelId;
	}

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
		return "ProgramLevel [programLevelId=" + programLevelId + ", levelName=" + levelName + ", courses=" + courses
				+ "]";
	}

}
