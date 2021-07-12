package com.fanshawe.nfttracker.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanshawe.nfttracker.api.dao.TermDao;
import com.fanshawe.nfttracker.api.request.TermRequest;
import com.fanshawe.nfttracker.api.response.TermResponse;
import com.fanshawe.nfttracker.api.services.TermService;
import com.fanshawe.nfttracker.helper.ApplicationHelper;

@Service
public class TermServiceImpl implements TermService {

	@Autowired
	TermDao termDao;

	@Override
	public TermResponse addTerms(List<TermRequest> terms) {
		TermResponse response = new TermResponse();
		try {
			response = termDao.addTerm(terms);
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public TermResponse getTerms() {
		TermResponse response = new TermResponse();
		try {
			response = termDao.getTerms();
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public TermResponse getTermByTermName(String termName) {
		TermResponse response = new TermResponse();
		try {
			response = termDao.getTermByTermName(termName);
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public TermResponse updateTermName(TermRequest termRequest, String termName) {
		TermResponse response = new TermResponse();
		try {
			response = termDao.updateTermName(termRequest, termName);
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

	@Override
	public TermResponse deleteTerm(String termName) {
		TermResponse response = new TermResponse();
		try {
			response = termDao.deleteTerm(termName);
		} catch (Exception e) {
			ApplicationHelper.configureFailureResponseWithException(response, e.getMessage(), e);
		}
		return response;
	}

}
