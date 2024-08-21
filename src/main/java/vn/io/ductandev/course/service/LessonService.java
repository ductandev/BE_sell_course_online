package vn.io.ductandev.course.service;

import java.util.List;

import vn.io.ductandev.course.dto.LessonDTO;
import vn.io.ductandev.course.request.LessonRequest;

public interface LessonService {

	List<LessonDTO> getListVideo();
	
	boolean addVideo(LessonRequest lessonRequest);
	
	
}
