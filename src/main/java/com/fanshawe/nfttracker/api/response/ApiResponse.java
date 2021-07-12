package com.fanshawe.nfttracker.api.response;

import java.util.List;

public class ApiResponse {
	private String message;
	private boolean status;
	private List<Exception> errors;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Exception> getErrors() {
		return errors;
	}

	public void setErrors(List<Exception> list) {
		this.errors = list;
	}

	@Override
	public String toString() {
		return "ApiResponse [message=" + message + ", status=" + status + ", errors=" + errors + "]";
	}

}
