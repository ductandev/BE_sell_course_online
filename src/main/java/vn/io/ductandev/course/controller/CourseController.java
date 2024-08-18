package vn.io.ductandev.course.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.io.ductandev.course.dto.CourseDTO;
import vn.io.ductandev.course.dto.ICourseTopSale;
import vn.io.ductandev.course.response.ResponseDTO;
import vn.io.ductandev.course.service.CourseService;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	 @GetMapping
	 public ResponseEntity<?> getAllCourses() {
	     List<CourseDTO> courses = courseService.getListCourse();
	     ResponseDTO<CourseDTO> response = new ResponseDTO<>(
	                "Thành công !",
	                HttpStatus.OK.value(),
	                (List<CourseDTO>) courses,
	                new Date()
	        );
	        return new ResponseEntity<>(response, HttpStatus.OK);
	 }
	
	 @PostMapping
	   public ResponseEntity<?> addCourse(@RequestBody CourseDTO courseDTO) {
	        boolean isAdd = courseService.addCourse(courseDTO);
	        if (isAdd) {
	        	ResponseDTO<CourseDTO> response = new ResponseDTO<>(
	                    "Thành công !",
	                    HttpStatus.OK.value(),
	                    (CourseDTO) courseDTO,
	                    new Date()
	            );
	            
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Failed to add course", HttpStatus.BAD_REQUEST);
	        }
	    }
	 
	 @GetMapping("/top3")
	    public ResponseEntity<List<ICourseTopSale>> getTop3BestSellingCourses() {
	        List<ICourseTopSale> topCourses = courseService.getTop5BestSellingBooks();
	        return ResponseEntity.ok(topCourses);
	    }

}
