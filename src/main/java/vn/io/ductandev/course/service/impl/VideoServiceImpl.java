package vn.io.ductandev.course.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.io.ductandev.course.entity.CourseEntity;
import vn.io.ductandev.course.entity.VideoEntity;
import vn.io.ductandev.course.model.CourseDTO;
import vn.io.ductandev.course.model.VideoDTO;
import vn.io.ductandev.course.repository.CourseRepository;
import vn.io.ductandev.course.repository.VideoRepository;
import vn.io.ductandev.course.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService{

	@Autowired
	private VideoRepository videoRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public List<VideoDTO> getListVideo() {
		 List<VideoEntity> videoEntities = videoRepository.findAll();
	        List<VideoDTO> videoDTOs = new ArrayList<>();

	        for (VideoEntity videoEntity : videoEntities) {
	            VideoDTO videoDTO = new VideoDTO();
	            videoDTO.setId(videoEntity.getId());
	            videoDTO.setName(videoEntity.getName());
	            videoDTO.setVideoUrl(videoEntity.getVideoUrl());
	            videoDTO.setIsDelete(videoEntity.getIsDelete());

	            // Map CourseEntity to CourseDTO
	            CourseDTO courseDTO = new CourseDTO();
	            courseDTO.setId(videoEntity.getCourse().getId());
	            courseDTO.setTitle(videoEntity.getCourse().getTitle());
	            videoDTO.setCourse(courseDTO);

	            videoDTOs.add(videoDTO);
	        }

	        return videoDTOs;
	}

	@Override
	public boolean addVideo(VideoDTO videoDTO) {
		boolean isSuccess = false;
        try {
            VideoEntity video = new VideoEntity();
            video.setName(videoDTO.getName());
            video.setVideoUrl(videoDTO.getVideoUrl());
            video.setIsDelete(videoDTO.getIsDelete());

            CourseEntity course = courseRepository.getById(videoDTO.getCourse().getId());
            video.setCourse(course);

            videoRepository.save(video);
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
	}
	
	
}
