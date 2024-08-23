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
import vn.io.ductandev.course.response.ResponseObject;
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
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<?> getByIDCourses(@PathVariable int id) {
	     CourseDTO course = courseService.getCourseById(id);
	     
	     if(course != null) {
	    	 ResponseObject<CourseDTO> response = new ResponseObject<>(
		                "Thành công !",
		                HttpStatus.OK.value(),
		                course,
		                new Date()
		        );
		        return new ResponseEntity<>(response, HttpStatus.OK);
	     }else {
	    	 ResponseList<String> response = new ResponseList<>(
                     "Failed to get course: Course not found with id " + id,
                     HttpStatus.NOT_FOUND.value(),
                     null,
                     new Date()
             );
             return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	     }
	     
	    
	 }
	 
	 @GetMapping("/top5")
	    public ResponseEntity<?> getTop3BestSellingCourses() {
		 try {
        	 List<ICourseTopSale> listResponseDTOs = courseService.getTop5BestSellingBooks();
        	 if(!listResponseDTOs.isEmpty()) {
        		 ResponseList<ICourseTopSale> response = new ResponseList<>(
     	                "Thành công !",
     	                HttpStatus.OK.value(),
     	                (List<ICourseTopSale>) listResponseDTOs,
     	                new Date()
     	        );
     	        return new ResponseEntity<>(response, HttpStatus.OK);
        	 }else {
        		 ResponseObject<String> response = new ResponseObject<>(
                         "Failed to get calculateRevenue !",
                         HttpStatus.NOT_FOUND.value(),
                         null,
                         new Date()
                 );
                 return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			 ResponseObject<String> response = new ResponseObject<>(
	                    "An error occurred while retrieving the course: " + e.getMessage(),
	                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
	                    null,
	                    new Date()
	            );
	            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	 @PostMapping("/calculate")
	    public ResponseEntity<?> calculateRevenue(@RequestBody RevenueRequestDTO requestDTO) {
	        try {
	        	 List<RevenueResponseDTO> listResponseDTOs = courseService.getRevenueByMonthAndYear(requestDTO);
	        	 if(!listResponseDTOs.isEmpty()) {
	        		 ResponseList<RevenueResponseDTO> response = new ResponseList<>(
	     	                "Thành công !",
	     	                HttpStatus.OK.value(),
	     	                (List<RevenueResponseDTO>) listResponseDTOs,
	     	                new Date()
	     	        );
	     	        return new ResponseEntity<>(response, HttpStatus.OK);
	        	 }else {
	        		 ResponseObject<String> response = new ResponseObject<>(
	                         "Failed to get calculateRevenue !",
	                         HttpStatus.NOT_FOUND.value(),
	                         null,
	                         new Date()
	                 );
	                 return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
				}
			} catch (Exception e) {
				 ResponseObject<String> response = new ResponseObject<>(
		                    "An error occurred while retrieving the course: " + e.getMessage(),
		                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
		                    null,
		                    new Date()
		            );
		            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		        }
	    }

}
