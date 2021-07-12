package com.fanshawe.nfttracker.api.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Term {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long termId;

	@Column(nullable = false, unique = true)
	String termName;

	@OneToOne(cascade = CascadeType.REMOVE)
	TermSheet termSheet;

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	public TermSheet getTermSheet() {
		return termSheet;
	}

	public void setTermSheet(TermSheet termSheet) {
		this.termSheet = termSheet;
	}

	@Override
	public String toString() {
		return "Term [termId=" + termId + ", termName=" + termName + ", termSheet=" + termSheet + "]";
	}

}
