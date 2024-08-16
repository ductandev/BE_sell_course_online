package vn.io.ductandev.course.service;

import java.util.List;

import vn.io.ductandev.course.model.PersonCourseDTO;

public interface PersonCourseService {

	 List<PersonCourseDTO> getAllPersonCourses();
	 
	 
	 
	 boolean addPersonCourse(PersonCourseDTO personCourseDTO);
	
}
