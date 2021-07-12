package com.fanshawe.nfttracker.api.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fanshawe.nfttracker.api.entities.TermBlock;

public class TermSheetRequest {

	List<TermBlock> listOfTermBlocks;

	String termSheetName;

	String termName;

	Map<String, Long> professorAllotedHours = new HashMap<String, Long>();

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

	public Map<String, Long> getProfessorAllotedHours() {
		return professorAllotedHours;
	}

	public void setProfessorAllotedHours(Map<String, Long> professorAllotedHours) {
		this.professorAllotedHours = professorAllotedHours;
	}

}
