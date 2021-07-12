package com.fanshawe.nfttracker.api.request;

import com.fanshawe.nfttracker.api.entities.TermSheet;

public class TermRequest {

	private String termName;

	private TermSheet termSheet;

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName.toUpperCase();
	}

	public TermSheet getTermSheet() {
		return termSheet;
	}

	public void setTermSheet(TermSheet termSheet) {
		this.termSheet = termSheet;
	}

	@Override
	public String toString() {
		return "TermRequest [termName=" + termName + ", termSheet=" + termSheet + "]";
	}

}
