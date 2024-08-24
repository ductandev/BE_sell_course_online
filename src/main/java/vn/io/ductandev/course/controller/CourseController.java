package vn.io.ductandev.course.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import vn.io.ductandev.course.dto.CourseDTO;
import vn.io.ductandev.course.dto.ICourseTopSale;
import vn.io.ductandev.course.dto.RevenueRequestDTO;
import vn.io.ductandev.course.dto.RevenueResponseDTO;
import vn.io.ductandev.course.entity.CourseEntity;
import vn.io.ductandev.course.request.CategoryRequest;
import vn.io.ductandev.course.request.CourseRequest;
import vn.io.ductandev.course.request.CourseRequestUpdate;
import vn.io.ductandev.course.request.CourseSearchRequest;
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


    // ================================================
    //               	GET ALL COURSE
    // ================================================
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

    // ================================================
    //              GET COURSE BY ID
    // ================================================
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

    // ================================================
    //               	CREATE COURSE
    // ================================================
    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody CourseRequest courseRequest) {
        CourseEntity course = courseService.addCourse(courseRequest);
        if (course != null) {
            ResponseObject<CourseRequest> response = new ResponseObject<>(
                    "Thành công !",
                    HttpStatus.OK.value(),
                    courseRequest,
                    new Date()
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to add course", HttpStatus.BAD_REQUEST);
        }
    }


    // ================================================
    //            GET TOP 3 COURSE BEST SELLING
    // ================================================
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

    // ================================================
    //          GET REVENUE BY MONTH AND YEAR
    // ================================================
    @PostMapping("/revenue")
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


    
    @PatchMapping("/{id}")
	public ResponseEntity<?> updateLesson(@PathVariable int id, @RequestBody CourseRequestUpdate courseRequestUpdate) {
    	CourseRequestUpdate isUpdate  = courseService.updateCourse(id, courseRequestUpdate);
        if (isUpdate != null) {
        	ResponseObject<CourseRequestUpdate> response = new ResponseObject<>(
                    "Thành công !",
                    HttpStatus.OK.value(),
                    isUpdate,
                    new Date()
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
        	ResponseObject<CategoryRequest> response = new ResponseObject<>(
                     "Failed to update Course: Course not found with id " + id,
                     HttpStatus.NOT_FOUND.value(),
                     null,
                     new Date()
             );
             return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
	}
    
    
    @DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable int id) {
    	CourseDTO isDelete  = courseService.deleteCourse(id);
	    
	    if (isDelete != null) {
        	ResponseObject<CourseDTO> response = new ResponseObject<>(
                    "Thành công !",
                    HttpStatus.OK.value(),
                    isDelete,
                    new Date()
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
        	 ResponseList<String> response = new ResponseList<>(
                     "Failed to delete course: Course not found with id " + id,
                     HttpStatus.NOT_FOUND.value(),
                     null,
                     new Date()
             );
             return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
	}

    @GetMapping("/search")
    public ResponseEntity<?> searchCourses(@RequestParam String title, 
                                           @RequestParam int categoryId, 
                                           @RequestParam(defaultValue = "0") int page, 
                                           @RequestParam(defaultValue = "10") int limit) {
        try {
            Page<CourseDTO> courses = courseService.getCoursesByTitleAndCategory(title, categoryId, page, limit);
            CourseSearchRequest<CourseDTO> response = new CourseSearchRequest<>(
                    "Thành công !",
                    HttpStatus.OK.value(),
                    courses,
                    new Date()
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching courses.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
