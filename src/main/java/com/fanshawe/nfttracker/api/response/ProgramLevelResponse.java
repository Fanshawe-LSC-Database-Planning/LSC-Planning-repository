package com.fanshawe.nfttracker.api.response;

import java.util.ArrayList;
import java.util.List;

import com.fanshawe.nfttracker.api.entities.ProgramLevel;

public class ProgramLevelResponse extends ApiResponse {

	private List<ProgramLevel> programLevels = new ArrayList<ProgramLevel>();

	public List<ProgramLevel> getProgramLevels() {
		return programLevels;
	}

	public void setProgramLevels(List<ProgramLevel> programLevels) {
		this.programLevels = programLevels;
	}

	@Override
	public String toString() {
		return "ProgramLevelResponse [programLevels=" + programLevels + "]";
	}

}
