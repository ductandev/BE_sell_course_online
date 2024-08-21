package vn.io.ductandev.course.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.io.ductandev.course.entity.CourseEntity;
import vn.io.ductandev.course.entity.LessonEntity;
import vn.io.ductandev.course.dto.CourseDTO;
import vn.io.ductandev.course.dto.LessonDTO;
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
	public List<LessonDTO> getListVideo() {
		 List<LessonEntity> videoEntities = videoRepository.findAll();
	        List<LessonDTO> lessonDTOS = new ArrayList<>();

	        for (LessonEntity videoEntity : videoEntities) {
	            LessonDTO lessonDTO = new LessonDTO();
	            lessonDTO.setId(videoEntity.getId());
	            lessonDTO.setName(videoEntity.getName());
	            lessonDTO.setVideoUrl(videoEntity.getVideoUrl());
	            lessonDTO.setIsDelete(videoEntity.getIsDelete());

	            // Map CourseEntity to CourseDTO
	            CourseDTO courseDTO = new CourseDTO();
	            courseDTO.setId(videoEntity.getCourse().getId());
	            courseDTO.setTitle(videoEntity.getCourse().getTitle());
	            courseDTO.setPrice(videoEntity.getCourse().getPrice());
	            courseDTO.setLecturer(videoEntity.getCourse().getLecturer());
	            courseDTO.setCreateDate(videoEntity.getCourse().getCreateDate());
	            courseDTO.setImage(videoEntity.getCourse().getImage());
	            courseDTO.setDescription(videoEntity.getCourse().getDescription());
	            courseDTO.setIsTopCourse(videoEntity.getCourse().getIsTopCourse());
	            courseDTO.setIsDelete(videoEntity.getCourse().getIsDelete());
//	            courseDTO.set(videoEntity.getCourse().getDescription());
	            
	            lessonDTO.setCourse(courseDTO);

	            lessonDTOS.add(lessonDTO);
	        }

	        return lessonDTOS;
	}

	@Override
	public boolean addVideo(LessonDTO lessonDTO) {
		boolean isSuccess = false;
        try {
            LessonEntity video = new LessonEntity();
            video.setName(lessonDTO.getName());
            video.setVideoUrl(lessonDTO.getVideoUrl());
            video.setIsDelete(lessonDTO.getIsDelete());

            CourseEntity course = courseRepository.getById(lessonDTO.getCourse().getId());
            video.setCourse(course);

            videoRepository.save(video);
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
	}
	
	
}
