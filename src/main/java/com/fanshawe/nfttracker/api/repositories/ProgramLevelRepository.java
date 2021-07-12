package com.fanshawe.nfttracker.api.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fanshawe.nfttracker.api.entities.ProgramLevel;

@Repository
public interface ProgramLevelRepository extends CrudRepository<ProgramLevel, Long> {

	public List<ProgramLevel> findByLevelName(String levelName);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update program_level set program_id = :programId where level_name IN :levelNames")
	public int addProgramLevelInProgram(@Param("programId") Long programId,
			@Param("levelNames") List<String> levelNames);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update program_level set program_id = null where level_name IN :levelNames")
	public int removeProgramLevelFromProgram(@Param("levelNames") List<String> levelNames);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update program_level set program_id = null where program_level_id =:levelId")
	public int removeProgramLevelFromProgramById(@Param("levelId") Long levelId);

}
