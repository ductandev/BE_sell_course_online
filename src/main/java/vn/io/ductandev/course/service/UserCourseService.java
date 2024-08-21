package vn.io.ductandev.course.service;

import java.util.List;

import vn.io.ductandev.course.dto.UserCourseDTO;



public interface UserCourseService {

	 List<UserCourseDTO> getAllPersonCourses();
	 
	 boolean addPersonCourse(UserCourseDTO userCourseDTO);
	 
	
}
