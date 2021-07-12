package com.fanshawe.nfttracker.helper;

import java.util.Arrays;

import com.fanshawe.nfttracker.api.response.ApiResponse;

public class ApplicationHelper {
	public static void configureFailureResponseWithException(ApiResponse response, String message, Exception e) {
		response.setStatus(Boolean.FALSE);
		response.setMessage(message);
		response.setErrors(Arrays.asList(e));
	}

	public static void configureFailureResponseWithoutException(ApiResponse response, String message) {
		response.setStatus(Boolean.FALSE);
		response.setMessage(message);
	}

}
