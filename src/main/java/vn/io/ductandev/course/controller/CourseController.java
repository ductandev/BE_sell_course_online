package vn.io.ductandev.course.controller;

import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.io.ductandev.course.dto.*;
import vn.io.ductandev.course.response.ResponseList;
import vn.io.ductandev.course.service.CourseService;

@Tag(name = "Course")
@RestController
@RequestMapping("/api/course")
@CrossOrigin
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	 @GetMapping
	 public ResponseEntity<?> getAllCourses() {
	     List<CourseDTO> courses = courseService.getListCourse();
	     ResponseList<CourseDTO> response = new ResponseList<>(
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
	        	ResponseList<CourseDTO> response = new ResponseList<>(
	                    "Thành công !",
	                    HttpStatus.OK.value(),
						(List<CourseDTO>) courseDTO,
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
	 
	 @PostMapping("/calculate")
	    public ResponseEntity<List<RevenueResponseDTO>> calculateRevenue(@RequestBody RevenueRequestDTO requestDTO) {
	        List<RevenueResponseDTO> response = courseService.getRevenueByMonthAndYear(requestDTO);
	        return ResponseEntity.ok(response);
	    }

}
