package vn.io.ductandev.course.service;

import java.util.List;
import vn.io.ductandev.course.dto.LessonByIdDTO;
import vn.io.ductandev.course.dto.LessonDTO;
import vn.io.ductandev.course.entity.LessonEntity;
import vn.io.ductandev.course.request.LessonRequest;
import vn.io.ductandev.course.request.LessonRequestUpdate;

public interface LessonService {

	List<LessonDTO> getListVideo();
	
	boolean addLesson(LessonRequest lessonRequest);
	
	LessonByIdDTO getByID(int id);
	
	LessonRequestUpdate updateLesson(int id, LessonRequestUpdate lessonRequest);
	
	LessonDTO deleteLesson(int id);
	
}
