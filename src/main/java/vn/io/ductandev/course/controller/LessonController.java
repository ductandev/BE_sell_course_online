package vn.io.ductandev.course.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import vn.io.ductandev.course.dto.LessonByIdDTO;
import vn.io.ductandev.course.dto.LessonDTO;
import vn.io.ductandev.course.request.LessonRequest;
import vn.io.ductandev.course.request.LessonRequestPatch;
import vn.io.ductandev.course.response.ResponseList;

import vn.io.ductandev.course.response.ResponseObject;
import vn.io.ductandev.course.service.LessonService;

@Tag(name = "Lesson")
@RestController
@RequestMapping("/api/lesson")
public class LessonController {

	@Autowired
    private LessonService lessonService;


    // ================================================
    //                GET ALL LESSION
    // ================================================
    @GetMapping
    public ResponseEntity<?> getAllVideos() {
        List<LessonDTO> videos = lessonService.getListVideo();
        ResponseList<LessonDTO> response = new ResponseList<>(
                "Thành công !",
                HttpStatus.OK.value(),
                (List<LessonDTO>) videos,
                new Date()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // ================================================
    //                CREATE LESSION
    // ================================================
    @PostMapping
    public ResponseEntity<?> addVideo(@RequestBody LessonRequest lessonRequest) {
        boolean isAdd = lessonService.addLesson(lessonRequest);
        if (isAdd) {
        	ResponseObject<LessonRequest> response = new ResponseObject<>(
                    "Thành công !",
                    HttpStatus.CREATED.value(),
                    lessonRequest,
                    new Date()
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to add video", HttpStatus.BAD_REQUEST);
        }
    }

    // ================================================
    //                GET LESSION BY ID
    // ================================================
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        LessonByIdDTO lesson = lessonService.getByID(id);
        ResponseObject<LessonByIdDTO> response = new ResponseObject<>(
                "Thành công !",
                HttpStatus.OK.value(),
                lesson,
                new Date()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // ================================================
    //             UPDATE LESSION BY ID
    // ================================================
    @PutMapping("/{id}")
    public ResponseEntity<?> updateLesson(@PathVariable int id, @RequestBody LessonRequestPatch lessonRequestPatch) {
        LessonByIdDTO lesson = lessonService.updateLesson(id, lessonRequestPatch);

        if (lesson != null) {
            ResponseObject<LessonByIdDTO> response = new ResponseObject<>(
                    "Update success!",
                    HttpStatus.OK.value(),
                    lesson,
                    new Date()
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponseObject<String> response = new ResponseObject<>(
                    "Failed to update lesson ID:" + id,
                    HttpStatus.NOT_FOUND.value(),
                    null,
                    new Date()
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


    // ================================================
    //             DELETE LESSION BY ID
    // ================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLesson(@PathVariable int id){
        LessonDTO lesson = lessonService.deleteLesson(id);

        if (lesson != null) {
            ResponseObject<LessonDTO> response = new ResponseObject<>(
                    "Deleted success!",
                    HttpStatus.OK.value(),
                    lesson,
                    new Date()
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponseObject<String> response = new ResponseObject<>(
                    "Failed to update lesson ID:" + id,
                    HttpStatus.NOT_FOUND.value(),
                    null,
                    new Date()
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
