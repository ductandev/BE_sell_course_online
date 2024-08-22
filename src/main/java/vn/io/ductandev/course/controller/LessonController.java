package vn.io.ductandev.course.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import vn.io.ductandev.course.dto.LessonByIdDTO;
import vn.io.ductandev.course.dto.LessonDTO;
import vn.io.ductandev.course.request.LessonRequest;
import vn.io.ductandev.course.response.ResponseList;

import vn.io.ductandev.course.response.ResponseObject;
import vn.io.ductandev.course.service.LessonService;

@Tag(name = "Lesson")
@RestController
@RequestMapping("/api/lesson")
public class LessonController {

	@Autowired
    private LessonService lessonService;

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
//
    @PostMapping
    public ResponseEntity<?> addVideo(@RequestBody LessonRequest lessonRequest) {
        boolean isAdd = lessonService.addLesson(lessonRequest);
        if (isAdd) {
        	ResponseObject<LessonRequest> response = new ResponseObject<>(
                    "Thành công !",
                    HttpStatus.OK.value(),
                    lessonRequest,
                    new Date()
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to add video", HttpStatus.BAD_REQUEST);
        }
    }
//    Test
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
	
}
