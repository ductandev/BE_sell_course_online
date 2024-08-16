package vn.io.ductandev.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.io.ductandev.course.dto.VideoDTO;
import vn.io.ductandev.course.service.VideoService;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

	@Autowired
    private VideoService videoService;

    @GetMapping
    public ResponseEntity<List<VideoDTO>> getAllVideos() {
        List<VideoDTO> videos = videoService.getListVideo();
        return ResponseEntity.ok(videos);
    }

    @PostMapping
    public ResponseEntity<Boolean> addVideo(@RequestBody VideoDTO videoDTO) {
        boolean isAdded = videoService.addVideo(videoDTO);
        if (isAdded) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
	
}
