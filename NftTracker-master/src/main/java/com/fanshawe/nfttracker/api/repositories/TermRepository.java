package com.fanshawe.nfttracker.api.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fanshawe.nfttracker.api.entities.Term;

@Repository
public interface TermRepository extends CrudRepository<Term, Long> {
	List<Term> findByTermName(String termName);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update term set term_sheet_term_sheet_id = :termSheetId where term_name = :termName")
	public int linkTermSheetToTerm(@Param("termSheetId") Long termSheetId, @Param("termName") String termName);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update term set term_sheet_term_sheet_id = NULL where term_id = :termId")
	public int unlinkTermSheetToTerm(@Param("termId") Long termId);

}
