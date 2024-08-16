package vn.io.ductandev.course.service;

import java.util.List;

import vn.io.ductandev.course.dto.CourseDTO;

public interface CourseService {

	List<CourseDTO> getListCourse();
	
	boolean addCourse(CourseDTO courseDTO);
	
	boolean getCourseById(int id);
	
	boolean updateCourse(int id, CourseDTO courseDTO);
	
	boolean deleteCourse(int id);
	
	
}
