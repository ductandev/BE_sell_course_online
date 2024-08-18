package vn.io.ductandev.course.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.io.ductandev.course.dto.PersonCourseDTO;
import vn.io.ductandev.course.service.PersonCourseService;

@Tag(name = "Person Course")
@RestController
@RequestMapping("/api/v1/person-courses")
public class PersonCourseController {

	 @Autowired
	 private PersonCourseService personCourseService;

	 @PostMapping
	 public ResponseEntity<Boolean> addPersonCourse(@RequestBody PersonCourseDTO personCourseDTO) {
	        boolean isAdded = personCourseService.addPersonCourse(personCourseDTO);
	        if (isAdded) {
	            return ResponseEntity.ok(true);
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	        }
	    }
	 
}
