package vn.io.ductandev.course.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import vn.io.ductandev.course.dto.CategoryDTO;
import vn.io.ductandev.course.dto.LessonByIdDTO;
import vn.io.ductandev.course.dto.LessonDTO;
import vn.io.ductandev.course.request.CategoryRequest;
import vn.io.ductandev.course.request.LessonRequest;
import vn.io.ductandev.course.request.LessonRequestUpdate;
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
    
    @PatchMapping("/{id}")
	public ResponseEntity<?> updateLesson(@PathVariable int id, @RequestBody LessonRequestUpdate lessonRequestUpdate) {
    	LessonRequestUpdate isUpdate  = lessonService.updateLesson(id, lessonRequestUpdate);
        if (isUpdate != null) {
        	ResponseObject<LessonRequestUpdate> response = new ResponseObject<>(
                    "Thành công !",
                    HttpStatus.OK.value(),
                    lessonRequestUpdate,
                    new Date()
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
        	ResponseObject<CategoryRequest> response = new ResponseObject<>(
                     "Failed to update lesson: Lesson not found with id " + id,
                     HttpStatus.NOT_FOUND.value(),
                     null,
                     new Date()
             );
             return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
	}
    
    
    @DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable int id) {
    	LessonDTO isDelete  = lessonService.deleteLesson(id);
	    
	    if (isDelete != null) {
        	ResponseObject<LessonDTO> response = new ResponseObject<>(
                    "Thành công !",
                    HttpStatus.OK.value(),
                    isDelete,
                    new Date()
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
        	 ResponseList<String> response = new ResponseList<>(
                     "Failed to delete lesson: Lesson not found with id " + id,
                     HttpStatus.NOT_FOUND.value(),
                     null,
                     new Date()
             );
             return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
	}
	
}
