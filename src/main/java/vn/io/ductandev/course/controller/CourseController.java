package vn.io.ductandev.course.controller;

import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.io.ductandev.course.dto.*;
import vn.io.ductandev.course.entity.CourseEntity;
import vn.io.ductandev.course.request.CourseRequest;
import vn.io.ductandev.course.request.CourseRequestPatch;
import vn.io.ductandev.course.response.ResponseList;
import vn.io.ductandev.course.response.ResponseListPagination;
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
    //                GET COURSE LIST
    // ================================================
    @GetMapping
    public ResponseEntity<ResponseListPagination<CourseDTO>> getCourses(
            @RequestParam(required = false) String searchByName,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) Integer categoryID) {

        try {
            List<CourseDTO> courses;
            if (categoryID == null) {
                // Nếu categoryID là null, tìm tất cả khóa học không phân biệt category
                courses = courseService.getListCourse(searchByName, page, limit, null);
            } else {
                courses = courseService.getListCourse(searchByName, page, limit, categoryID);
            }

            ResponseListPagination<CourseDTO> response = new ResponseListPagination<>(
                    "Thành công!",
                    HttpStatus.OK.value(),
                    courses,
                    courses.size(),
                    page,
                    limit,
                    new Date()
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // ================================================
    //              GET COURSE BY ID
    // ================================================
    @GetMapping("/{id}")
    public ResponseEntity<?> getByIDCourses(@PathVariable int id) {
        CourseDTO course = courseService.getCourseById(id);

        if (course != null) {
            ResponseObject<CourseDTO> response = new ResponseObject<>(
                    "Thành công !",
                    HttpStatus.OK.value(),
                    course,
                    new Date()
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
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
                    HttpStatus.CREATED.value(),
                    courseRequest,
                    new Date()
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to add course", HttpStatus.BAD_REQUEST);
        }
    }

    // ================================================
    //               	UPDATE COURSE
    // ================================================
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourseById(@PathVariable int id, @RequestBody CourseRequestPatch courseRequestPatch) {
        CourseDTO course = courseService.updateCourseById(id, courseRequestPatch);

        if (course != null) {
            ResponseObject<CourseDTO> response = new ResponseObject<>(
                    "Update success!",
                    HttpStatus.OK.value(),
                    course,
                    new Date()
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponseObject<String> response = new ResponseObject<>(
                    "Failed to update Course ID:" + id,
                    HttpStatus.NOT_FOUND.value(),
                    null,
                    new Date()
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


    // ================================================
    //            GET TOP 3 COURSE BEST SELLING
    // ================================================
    @GetMapping("/top5")
    public ResponseEntity<?> getTop3BestSellingCourses() {
        try {
            List<ICourseTopSale> listResponseDTOs = courseService.getTop5BestSellingBooks();
            if (!listResponseDTOs.isEmpty()) {
                ResponseList<ICourseTopSale> response = new ResponseList<>(
                        "Thành công !",
                        HttpStatus.OK.value(),
                        (List<ICourseTopSale>) listResponseDTOs,
                        new Date()
                );
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
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
            if (!listResponseDTOs.isEmpty()) {
                ResponseList<RevenueResponseDTO> response = new ResponseList<>(
                        "Thành công !",
                        HttpStatus.OK.value(),
                        (List<RevenueResponseDTO>) listResponseDTOs,
                        new Date()
                );
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
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
    //               	DELETE COURSE
    // ================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable int id){
        CourseDTO course = courseService.deleteCourseById(id);

        if (course != null) {
            ResponseObject<CourseDTO> response = new ResponseObject<>(
                    "Deleted success!",
                    HttpStatus.OK.value(),
                    course,
                    new Date()
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponseObject<String> response = new ResponseObject<>(
                    "Failed to delete course ID:" + id,
                    HttpStatus.NOT_FOUND.value(),
                    null,
                    new Date()
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


}
