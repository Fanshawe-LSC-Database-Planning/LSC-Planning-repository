package com.fanshawe.nfttracker.api.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

@Entity
public class TermSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long termSheetId;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "termSheetId")
	List<TermBlock> listOfTermBlocks;

	String termSheetName;

	String termName;

	@ElementCollection
	@MapKeyColumn(name = "professorId")
	@Column(name = "hours")
	@CollectionTable(name = "professor_Hours", joinColumns = @JoinColumn(name = "termSheetId"))
	Map<Long, Long> professorAllotedHours = new HashMap<Long, Long>();

	public List<TermBlock> getListOfTermBlocks() {
		return listOfTermBlocks;
	}

	public void setListOfTermBlocks(List<TermBlock> listOfTermBlocks) {
		this.listOfTermBlocks = listOfTermBlocks;
	}

	public String getTermSheetName() {
		return termSheetName;
	}

	public void setTermSheetName(String termSheetName) {
		this.termSheetName = termSheetName;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public Long getTermSheetId() {
		return termSheetId;
	}

	public void setTermSheetId(Long termSheetId) {
		this.termSheetId = termSheetId;
	}

	public Map<Long, Long> getProfessorAllotedHours() {
		return professorAllotedHours;
	}

	public void setProfessorAllotedHours(Map<Long, Long> professorAllotedHours) {
		this.professorAllotedHours = professorAllotedHours;
	}

	@Override
	public String toString() {
		return "TermSheet [termSheetId=" + termSheetId + ", listOfTermBlocks=" + listOfTermBlocks + ", termSheetName="
				+ termSheetName + ", termName=" + termName + ", professorAllotedHours=" + professorAllotedHours + "]";
	}

}
