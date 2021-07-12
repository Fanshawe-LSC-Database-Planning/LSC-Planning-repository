package com.fanshawe.nfttracker.api.response;

import java.util.ArrayList;
import java.util.List;

import com.fanshawe.nfttracker.api.entities.Program;

public class ProgramResponse extends ApiResponse {

	private List<Program> programs = new ArrayList<Program>();

	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

	@Override
	public String toString() {
		return "ProgramResponse [programs=" + programs + "]";
	}

}
