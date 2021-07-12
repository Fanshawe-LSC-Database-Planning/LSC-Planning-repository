package com.fanshawe.nfttracker.api.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fanshawe.nfttracker.api.entities.TermSheet;

@Repository
public interface TermSheetRepository extends CrudRepository<TermSheet, Long> {
	List<TermSheet> findByTermSheetName(String termSheetName);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update term_block set term_sheet_id = (select term_sheet_id from term_sheet where term_sheet_name = :termSheetName) where term_block_id = :termBlockId")
	public int linkTermBlockToTermSheet(@Param("termBlockId") Long termBlockId,
			@Param("termSheetName") String termSheetName);

	List<TermSheet> findByTermSheetId(Long termSheetId);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update professor_status set status=:status where course_info_id in( select ci.course_info_id from term_sheet ts join term_block tb on ts.term_sheet_id = tb.term_sheet_id join course_info ci on ci.term_block_id = tb.term_block_id join assigned_professors ap on ap.course_info_id = ci.course_info_id where ap.assinged_professors=:professorId and ts.term_sheet_id=:termSheetId) and professor_id=:professorId")
	public int modifyStatus(@Param("professorId") Long professorId, @Param("termSheetId") Long termSheetId,
			@Param("status") String status);

}
