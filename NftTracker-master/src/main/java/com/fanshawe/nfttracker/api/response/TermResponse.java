package com.fanshawe.nfttracker.api.response;

import java.util.List;

import com.fanshawe.nfttracker.api.entities.Term;

public class TermResponse extends ApiResponse {

	private List<Term> terms;

	public List<Term> getTerms() {
		return terms;
	}

	public void setTerms(List<Term> terms) {
		this.terms = terms;
	}

	@Override
	public String toString() {
		return "TermResponse [terms=" + terms + "]";
	}

}
