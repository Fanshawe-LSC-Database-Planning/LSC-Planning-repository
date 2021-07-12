package com.fanshawe.nfttracker.api.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fanshawe.nfttracker.api.entities.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "select * from course where course_name = :courseName")
	public List<Course> findCourseByCourseName(@Param("courseName") String courseName);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update course set program_level_id = :programLevelId where course_code IN :courseCodes")
	public int addCoursesInProgramLevel(@Param("programLevelId") Long programLevelId,
			@Param("courseCodes") List<String> courseCodes);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update course set program_level_id = null where course_code IN :courseCodes")
	public int removeCoursesFromProgramLevel(@Param("courseCodes") List<String> courseCodes);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "insert into suggested_professors(professor_id, course_id) values(:professorId,:courseId)")
	public int assignCoursetoProfessor(@Param("professorId") Long professorId, @Param("courseId") Long courseId);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "delete from suggested_professors where professor_id=:professorId and course_id=:courseIds")
	public int deassignCourseFromProfessor(@Param("professorId") Long professorId,
			@Param("courseIds") List<Long> courseIds);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "select p.program_name, pl.level_name, c.course_code, c.course_name from program p join program_level pl on p.program_id = pl.program_id join course c on c.program_level_id = pl.program_level_id where c.course_id not in (select s.course_id from course c join suggested_professors s on c.course_id=s.course_id where s.professor_id=:professorId)")
	public List<Object[]> findAllUnAssignedCourses(@Param("professorId") Long professorId);

	List<Course> findByCourseCode(String courseCode);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "INSERT INTO public.course (course_code, course_credit, course_name, program_level_id) VALUES(:courseCode, :courseCredit, :courseName, :programLevelId)")
	public int addCourseToProgramLevel(@Param("programLevelId") Long programLevelId,
			@Param("courseName") String courseName, @Param("courseCode") String courseCode,
			@Param("courseCredit") Long courseCredit);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update professor_hours set hours = hours- :courseCredit where professor_id IN :professor_ids")
	public int decreaseProfessorHoursOnCourseDeletion(@Param("professor_ids") List<Long> professor_ids,
			@Param("courseCredit") Long courseCredit);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update professor_hours set hours = hours+ :courseCredit where professor_id IN :professor_ids")
	public int updateProfessorHoursOnCourseDeletion(@Param("professor_ids") List<Long> professor_ids,
			@Param("courseCredit") Long courseCredit);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "delete from suggested_professors where course_id=:courseId")
	public int deleteSuggestedProfessorForCourseId(@Param("courseId") Long courseId);

}
