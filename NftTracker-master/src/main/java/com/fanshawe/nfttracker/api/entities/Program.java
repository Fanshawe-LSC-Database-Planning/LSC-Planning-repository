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
public class Program {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	Long programId;

	@Column(nullable = false, unique = true)
	String programName;

	@Column(nullable = false, unique = true)
	String programCode;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "programId")
	@Fetch(value = FetchMode.SUBSELECT)

	List<ProgramLevel> levels = new ArrayList<ProgramLevel>();

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public List<ProgramLevel> getLevels() {
		return levels;
	}

	public void setLevels(List<ProgramLevel> levels) {
		this.levels = levels;
	}

	@Override
	public String toString() {
		return "Program [programId=" + programId + ", programName=" + programName + ", programCode=" + programCode
				+ ", levels=" + levels + "]";
	}

}
