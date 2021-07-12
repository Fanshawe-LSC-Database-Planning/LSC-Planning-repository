package com.fanshawe.nfttracker.api.services;

import java.util.List;

import com.fanshawe.nfttracker.api.request.TermRequest;
import com.fanshawe.nfttracker.api.response.TermResponse;

public interface TermService {
	public TermResponse addTerms(List<TermRequest> terms);

	public TermResponse getTerms();

	public TermResponse getTermByTermName(String termName);

	public TermResponse updateTermName(TermRequest termRequest, String termName);

	public TermResponse deleteTerm(String termName);
}
