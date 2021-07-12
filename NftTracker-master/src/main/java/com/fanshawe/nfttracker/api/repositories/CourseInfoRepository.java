package com.fanshawe.nfttracker.api.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fanshawe.nfttracker.api.entities.CourseInfo;

@Repository
public interface CourseInfoRepository extends CrudRepository<CourseInfo, Long> {

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "select * from course_info where section_name = :sectionName and term_block_id = :termBlockId")
	public List<CourseInfo> findCoursesInfoBySectionName(@Param("sectionName") String sectionName,
			@Param("termBlockId") Long termBlockId);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "delete from course_info where term_block_id is NULL")
	public int deleteUnlinkedCourseInfos();

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "delete from assigned_professors where course_info_id=:courseInfoId")
	public int deleteAssignedCoursesToCourseInfo(@Param("courseInfoId") Long courseInfoId);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "delete from professor_status where course_info_id=:courseInfoId")
	public int deleteAssignedProfessorStatusToCourseInfo(@Param("courseInfoId") Long courseInfoId);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "delete from course_info where course_info_id=:courseInfoId")
	public int deleteCourseInfoByCourseInfoId(@Param("courseInfoId") Long courseInfoId);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "select count(*) from course_info where term_block_id=:termBlockId and course_code = :courseCode")
	public Object[] countCourseInfoByTermBlockId(@Param("termBlockId") Long termBlockId,
			@Param("courseCode") String courseCode);

	List<CourseInfo> findByCourseCode(String courseCode);

}
