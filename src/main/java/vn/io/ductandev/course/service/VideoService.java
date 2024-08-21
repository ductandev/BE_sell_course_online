package vn.io.ductandev.course.service;

import java.util.List;

import vn.io.ductandev.course.dto.LessonDTO;

public interface VideoService {

	List<LessonDTO> getListVideo();
	
	boolean addVideo(LessonDTO lessonDTO);
	
	
}
