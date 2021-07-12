package com.fanshawe.nfttracker.api.entities;

public enum ProfessorStatus {

	BLOCKED("blocked"), NEW_HIRE("newHire"), TEACHING_NOTIFICATION("teachingNotification"),
	CONTRACT_SENT_TO_FACULTY("contractSentToFaculty"), CONTRACT_SENT_TO_HR("contractSentToHR");

	private String status;

	ProfessorStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
