package vn.io.ductandev.course.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import vn.io.ductandev.course.dto.UserCourseDTO;
import vn.io.ductandev.course.response.ResponseObject;
import vn.io.ductandev.course.service.UserCourseService;

@Tag(name = "User Course")
@RestController
@RequestMapping("/api/user-courses")
public class UserCourseController {

	 @Autowired
	 private UserCourseService userCourseService;

	 @PostMapping
	 public ResponseEntity<?> addPersonCourse(@RequestBody UserCourseDTO userCourseDTO) {
	       
	        try {
				
		        boolean isAdded = userCourseService.addUserCourse(userCourseDTO);

		        if (isAdded) {
	                ResponseObject<String> response = new ResponseObject<>(
	                        "User retrieved successfully!",
	                        HttpStatus.OK.value(),
	                        "true",
	                        new Date()
	                );
	                return new ResponseEntity<>(response, HttpStatus.OK);
	            } else {
	                ResponseObject<String> response = new ResponseObject<>(
	                        "Failed to by course ",
	                        HttpStatus.NOT_FOUND.value(),
	                        "false",
	                        new Date()
	                );
	                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	            }
	        	
			} catch (Exception e) {
				  ResponseObject<String> response = new ResponseObject<>(
		                    "An error occurred while retrieving the user_course: " + e.getMessage(),
		                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
		                    null,
		                    new Date()
		            );
		            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	    }
	 
	 
	 
}
