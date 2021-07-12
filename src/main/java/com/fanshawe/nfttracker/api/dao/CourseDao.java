package com.fanshawe.nfttracker.api.dao;
import java.util.List;
import com.fanshawe.nfttracker.api.request.CourseRequest;
import com.fanshawe.nfttracker.api.response.CourseResponse;

public interface CourseDao {
	
	public CourseResponse addCourse(List<CourseRequest> course) throws Exception;

	public CourseResponse  getCourses();
	
	public CourseResponse getCourse(String courseName);
	
	public CourseResponse updateCourse(String courseName, CourseRequest course);

	public CourseResponse deleteCourse(String courseName);

}
