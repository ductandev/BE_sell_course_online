package vn.io.ductandev.course.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.io.ductandev.course.dto.UserCourseDTO;
import vn.io.ductandev.course.service.UserCourseService;

@Tag(name = "User Course")
@RestController
@RequestMapping("/api/user-courses")
public class UserCourseController {

	 @Autowired
	 private UserCourseService userCourseService;

	 @PostMapping
	 public ResponseEntity<Boolean> addPersonCourse(@RequestBody UserCourseDTO userCourseDTO) {
	        boolean isAdded = userCourseService.addPersonCourse(userCourseDTO);
	        if (isAdded) {
	            return ResponseEntity.ok(true);
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	        }
	    }
	 
	 
	 
}
