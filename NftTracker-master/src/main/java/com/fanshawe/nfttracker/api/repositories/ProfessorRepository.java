package com.fanshawe.nfttracker.api.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fanshawe.nfttracker.api.entities.Professor;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long> {

	List<Professor> findByEmailId(String emailId);

}