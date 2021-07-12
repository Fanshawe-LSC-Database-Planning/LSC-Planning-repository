package com.fanshawe.nfttracker.api.dao;

import java.util.List;

import com.fanshawe.nfttracker.api.request.TermRequest;
import com.fanshawe.nfttracker.api.response.TermResponse;

public interface TermDao {
	public TermResponse addTerm(List<TermRequest> terms) throws Exception;

	public TermResponse getTerms();

	public TermResponse getTermByTermName(String termName);

	public TermResponse updateTermName(TermRequest termRequest, String termName);

	public TermResponse deleteTerm(String termName);
}
