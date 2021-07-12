package com.fanshawe.nfttracker.api.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fanshawe.nfttracker.api.entities.Program;

@Repository
public interface ProgramRepository extends CrudRepository<Program, Long> {

	public List<Program> findByProgramCode(String programCode);

	public List<Program> findByProgramName(String programName);

}
