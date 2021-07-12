package com.fanshawe.nfttracker.api.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanshawe.nfttracker.api.dao.TermDao;
import com.fanshawe.nfttracker.api.entities.Term;
import com.fanshawe.nfttracker.api.repositories.TermRepository;
import com.fanshawe.nfttracker.api.request.TermRequest;
import com.fanshawe.nfttracker.api.response.TermResponse;
import com.fanshawe.nfttracker.helper.ApplicationHelper;
import com.fanshawe.nfttracker.helper.CopyListProperties;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class TermDaoImpl implements TermDao {
	@Autowired
	private TermRepository termRepo;

	@Override
	public TermResponse addTerm(List<TermRequest> terms) throws Exception {
		TermResponse response = new TermResponse();
		for (TermRequest termRequest : terms) {
			if (termRepo.findByTermName(termRequest.getTermName()).size() > 0) {
				response.setMessage("Term: " + termRequest.getTermName() + " already exists in the system");
				return response;
			}
		}
		List<Term> termEntities = new CopyListProperties(Term.class).copy(terms);
		List<Term> termSavedEntities = (List<Term>) termRepo.saveAll(termEntities);

		response.setTerms(new ArrayList<>());
		if (termSavedEntities != null && termEntities.size() > 0) {
			response.setTerms(termSavedEntities);
			response.setMessage("Term saved successfully");
			response.setStatus(Boolean.TRUE);
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response,
				"Some error occured while saving term details");
		return response;

	}

	@Override
	public TermResponse getTerms() {
		List<Term> terms = (List<Term>) termRepo.findAll();
		TermResponse response = new TermResponse();
		response.setStatus(Boolean.TRUE);
		if (terms != null && terms.size() > 0) {
			response.setTerms(terms);
			response.setStatus(Boolean.TRUE);
			response.setMessage("Term fetched successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response, "No term found in the database");
		return response;
	}

	@Override
	public TermResponse getTermByTermName(String termName) {
		List<Term> terms = (List<Term>) termRepo.findByTermName(termName);
		TermResponse response = new TermResponse();
		if (terms != null && terms.size() > 0) {
			response.setTerms(terms);
			response.setStatus(Boolean.TRUE);
			response.setMessage("Term fetched successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response, "Term not found in the database");
		return response;
	}

	@Override
	public TermResponse updateTermName(TermRequest termRequest, String termName) {
		List<Term> terms = (List<Term>) termRepo.findByTermName(termName);
		TermResponse response = new TermResponse();
		if (terms != null && terms.size() > 0) {
			terms.get(0).setTermName(termRequest.getTermName());
			Term termUpdated = termRepo.save(terms.get(0));
			if (termUpdated != null) {
				response.setTerms(Arrays.asList(termUpdated));
				response.setStatus(Boolean.TRUE);
				response.setMessage("Term updated successfully");
				return response;
			}
		}
		ApplicationHelper.configureFailureResponseWithoutException(response, "Term not found in the database");
		return response;

	}

	@Override
	public TermResponse deleteTerm(String termName) {
		List<Term> terms = (List<Term>) termRepo.findByTermName(termName);
		TermResponse response = new TermResponse();
		if (terms != null && terms.size() > 0) {
			termRepo.delete(terms.get(0));
			response.setTerms(Arrays.asList(terms.get(0)));
			response.setStatus(Boolean.TRUE);
			response.setMessage("Term deleted successfully");
			return response;
		}
		ApplicationHelper.configureFailureResponseWithoutException(response, "Term not found in the database");
		return response;
	}

}
