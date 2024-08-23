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
