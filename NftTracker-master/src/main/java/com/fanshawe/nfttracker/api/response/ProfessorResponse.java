package com.fanshawe.nfttracker.api.response;

import java.util.ArrayList;
import java.util.List;

import com.fanshawe.nfttracker.api.entities.Professor;

/**
 * @author Raman.Ahuja
 *
 *         Class to get a response to the api for user CRUD operations
 *
 */
public class ProfessorResponse extends ApiResponse {

	private List<Professor> professors = new ArrayList<Professor>();

	public List<Professor> getProfessors() {
		return professors;
	}

	public void setProfessors(List<Professor> professors) {
		this.professors = professors;
	}

	@Override
	public String toString() {
		return "ProfessorResponse [professors=" + professors + "]";
	}

}
