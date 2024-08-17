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
import vn.io.ductandev.course.dto.VideoDTO;
import vn.io.ductandev.course.response.ResponseDTO;
import vn.io.ductandev.course.service.VideoService;

@RestController
@RequestMapping("/api/v1/videos")
public class VideoController {

	@Autowired
    private VideoService videoService;

    @GetMapping
    public ResponseEntity<?> getAllVideos() {
        List<VideoDTO> videos = videoService.getListVideo();
        ResponseDTO<VideoDTO> response = new ResponseDTO<>(
                "Thành công !",
                HttpStatus.OK.value(),
                (List<VideoDTO>) videos,
                new Date()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addVideo(@RequestBody VideoDTO videoDTO) {
        boolean isAdd = videoService.addVideo(videoDTO);
        if (isAdd) {
        	ResponseDTO<VideoDTO> response = new ResponseDTO<>(
                    "Thành công !",
                    HttpStatus.OK.value(),
                    (VideoDTO) videoDTO,
                    new Date()
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to add video", HttpStatus.BAD_REQUEST);
        }
    }
	
}
