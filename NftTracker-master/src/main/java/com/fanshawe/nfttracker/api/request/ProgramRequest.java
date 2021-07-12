package com.fanshawe.nfttracker.api.request;

import java.util.ArrayList;
import java.util.List;

public class ProgramRequest {

	String programName;

	String programCode;

	List<ProgramLevelRequest> levels = new ArrayList<ProgramLevelRequest>();

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

	@Override
	public String toString() {
		return "ProgramRequest [programName=" + programName + ", programCode=" + programCode + "]";
	}

	public List<ProgramLevelRequest> getLevels() {
		return levels;
	}

	public void setLevels(List<ProgramLevelRequest> levels) {
		this.levels = levels;
	}

}
