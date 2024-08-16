package vn.io.ductandev.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.io.ductandev.course.model.CourseDTO;
import vn.io.ductandev.course.service.CourseService;

@RestController
@RequestMapping("/api/course")
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	 @GetMapping
	 public ResponseEntity<List<CourseDTO>> getAllCourses() {
	     List<CourseDTO> courses = courseService.getListCourse();
	     return ResponseEntity.ok(courses);
	 }
	
	 @PostMapping
	   public ResponseEntity<Boolean> addCourse(@RequestBody CourseDTO courseDTO) {
	        boolean isAdded = courseService.addCourse(courseDTO);
	        if (isAdded) {
	            return ResponseEntity.ok(true);
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	        }
	    }

}
