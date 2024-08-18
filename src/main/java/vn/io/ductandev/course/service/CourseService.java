package vn.io.ductandev.course.service;

import java.util.List;

import vn.io.ductandev.course.dto.CourseDTO;
import vn.io.ductandev.course.dto.ICourseTopSale;

public interface CourseService {

	List<CourseDTO> getListCourse();
	
	boolean addCourse(CourseDTO courseDTO);
	
	boolean getCourseById(int id);
	
	boolean updateCourse(int id, CourseDTO courseDTO);
	
	boolean deleteCourse(int id);
	
	List<ICourseTopSale> getTop5BestSellingBooks();	
}
