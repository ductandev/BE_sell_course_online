package vn.io.ductandev.course.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.io.ductandev.course.dto.CategoryDTO;
import vn.io.ductandev.course.dto.CourseDTO;
import vn.io.ductandev.course.dto.LessonByIdDTO;
import vn.io.ductandev.course.dto.LessonDTO;
import vn.io.ductandev.course.entity.CategoryEntity;
import vn.io.ductandev.course.entity.CourseEntity;
import vn.io.ductandev.course.entity.LessonEntity;
import vn.io.ductandev.course.repository.CourseRepository;
import vn.io.ductandev.course.repository.LessonRepository;
import vn.io.ductandev.course.request.LessonRequest;
import vn.io.ductandev.course.request.LessonRequestUpdate;
import vn.io.ductandev.course.service.LessonService;

@Service
public class LessonServiceImpl implements LessonService{

	@Autowired
	private LessonRepository lessonRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public List<LessonDTO> getListVideo() {
		 List<LessonEntity> videoEntities = lessonRepository.findAll();
	        List<LessonDTO> lessonDTOS = new ArrayList<>();

	        for (LessonEntity videoEntity : videoEntities) {
	            LessonDTO lessonDTO = new LessonDTO();
	            lessonDTO.setId(videoEntity.getId());
	            lessonDTO.setName(videoEntity.getName());
	            lessonDTO.setVideoUrl(videoEntity.getVideoUrl());
	            lessonDTO.setIsDelete(videoEntity.getIsDelete());

	            // Map CourseEntity to CourseDTO
//	            CourseDTO courseDTO = new CourseDTO();
//	            courseDTO.setId(videoEntity.getCourse().getId());
//	            courseDTO.setTitle(videoEntity.getCourse().getTitle());
//	            courseDTO.setPrice(videoEntity.getCourse().getPrice());
//	            courseDTO.setLecturer(videoEntity.getCourse().getLecturer());
//	            courseDTO.setCreateDate(videoEntity.getCourse().getCreateDate());
//	            courseDTO.setImage(videoEntity.getCourse().getImage());
//	            courseDTO.setDescription(videoEntity.getCourse().getDescription());
//	            courseDTO.setIsTopCourse(videoEntity.getCourse().getIsTopCourse());
//	            courseDTO.setIsDelete(videoEntity.getCourse().getIsDelete());
////	            courseDTO.set(videoEntity.getCourse().getDescription());
//	            
//	            lessonDTO.setCourse(courseDTO);

	            lessonDTOS.add(lessonDTO);
	        }

	        return lessonDTOS;
	}

	@Override

	public boolean addLesson(LessonRequest lessonRequest) {
		boolean isSuccess = false;
        try {
            LessonEntity video = new LessonEntity();
            video.setName(lessonRequest.name());
            video.setVideoUrl(lessonRequest.videoUrl());

            CourseEntity course = courseRepository.getById(lessonRequest.courseID());

            video.setCourse(course);

            lessonRepository.save(video);
            isSuccess = true;
            return isSuccess;
        } catch (Exception e) {
        	return isSuccess;
        }
        
	}

	@Override
	public LessonByIdDTO getByID(int id) {
		
		LessonEntity lessonEntity = lessonRepository.getById(id);
		
		LessonByIdDTO lessonDTO = new LessonByIdDTO();
		
		if(lessonEntity != null) {
			lessonDTO.setId(lessonEntity.getId());
			lessonDTO.setName(lessonEntity.getName());
			lessonDTO.setVideoUrl(lessonEntity.getVideoUrl());
			lessonDTO.setIsDelete(lessonEntity.getIsDelete());
			lessonDTO.setIsSuccess(lessonEntity.getIsSuccess());
			
			CourseEntity c = lessonEntity.getCourse();
			
			CourseDTO courseDTO = new CourseDTO();
			
			courseDTO.setId(c.getId());
			courseDTO.setTitle(c.getTitle());
			courseDTO.setPrice(c.getPrice());
			courseDTO.setLecturer(c.getLecturer());
			courseDTO.setImage(c.getImage());
			courseDTO.setDescription(c.getDescription());
			courseDTO.setCreateDate(c.getCreateDate());
			courseDTO.setIsTopCourse(c.getIsTopCourse());
			courseDTO.setIsFree(c.getIsFree());
			courseDTO.setIsPublic(c.getIsPublic());
			courseDTO.setIsDelete(c.getIsDelete());
			
			lessonDTO.setCourse(courseDTO);
		}
		
		return lessonDTO;
	}

	@Override
	public LessonRequestUpdate updateLesson(int id, LessonRequestUpdate lessonRequest) {
		LessonEntity lessonEntity = lessonRepository.getById(id);
		
		if(lessonEntity != null) {
			
			try {
				lessonEntity.setName(lessonRequest.name());
				lessonEntity.setVideoUrl(lessonRequest.videoUrl());
				lessonEntity.setIsSuccess(lessonRequest.isSuccess());
				lessonEntity.setIsDelete(lessonEntity.getIsDelete());
				
				lessonRepository.save(lessonEntity);
				
				return lessonRequest;
			} catch (Exception e) {
				System.out.println("Lỗi Update !");
			}
		}
			return null;
	}

	@Override
	public LessonDTO deleteLesson(int id) {
		try {
			
			LessonEntity lessonEntity = lessonRepository.getById(id);
			lessonEntity.setIsDelete(1);
			LessonDTO lessonDTO = new LessonDTO();
			
			if(lessonEntity != null) {
				lessonDTO.setIsDelete(lessonEntity.getIsDelete());
				lessonDTO.setId(lessonEntity.getId());
				lessonDTO.setName(lessonEntity.getName());
				lessonDTO.setIsDelete(lessonEntity.getIsDelete());
				lessonDTO.setIsSuccess(lessonEntity.getIsSuccess());
				lessonDTO.setVideoUrl(lessonEntity.getVideoUrl());
				
				lessonRepository.save(lessonEntity);
				
			}
			
			return lessonDTO;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	
}
