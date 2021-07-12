package com.fanshawe.nfttracker.api.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fanshawe.nfttracker.api.entities.TermBlock;

@Repository
public interface TermBlockRepository extends CrudRepository<TermBlock, Long> {

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update term_block set term_end_date = ':termEndDate',term_start_date = ':termStartDate', totalWeeks =:totalWeeks  where term_block_id = :termBlockId")
	public int updateTermBlock(@Param("termStartDate") String termStartDate, @Param("termEndDate") String termEndDate,
			@Param("totalWeeks") Long totalWeeks, @Param("termBlockId") Long termBlockId);

	List<TermBlock> findByLevelName(String levelName);

	List<TermBlock> findByProgramName(String programName);

}
