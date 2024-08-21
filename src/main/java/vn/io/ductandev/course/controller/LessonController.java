package vn.io.ductandev.course.controller;

import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.io.ductandev.course.dto.LessonDTO;
import vn.io.ductandev.course.response.ResponseList;
import vn.io.ductandev.course.service.VideoService;

@Tag(name = "Lesson")
@RestController
@RequestMapping("/api/lesson")
public class LessonController {

	@Autowired
    private VideoService videoService;

    @GetMapping
    public ResponseEntity<?> getAllVideos() {
        List<LessonDTO> videos = videoService.getListVideo();
        ResponseList<LessonDTO> response = new ResponseList<>(
                "Thành công !",
                HttpStatus.OK.value(),
                (List<LessonDTO>) videos,
                new Date()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addVideo(@RequestBody LessonDTO lessonDTO) {
        boolean isAdd = videoService.addVideo(lessonDTO);
        if (isAdd) {
        	ResponseList<LessonDTO> response = new ResponseList<>(
                    "Thành công !",
                    HttpStatus.OK.value(),
                    (List<LessonDTO>) lessonDTO,
                    new Date()
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to add video", HttpStatus.BAD_REQUEST);
        }
    }
	
}
