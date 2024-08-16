package vn.io.ductandev.course.model;

import java.util.Date;

import lombok.Data;

@Data
public class PersonCourseDTO {
	
	   private PersonDTO person;
	   private CourseDTO course;
	   private Date dateBuy;
}
