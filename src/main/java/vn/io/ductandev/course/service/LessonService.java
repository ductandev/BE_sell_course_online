package vn.io.ductandev.course.service;

import java.util.List;
import vn.io.ductandev.course.dto.LessonByIdDTO;
import vn.io.ductandev.course.dto.LessonDTO;
import vn.io.ductandev.course.entity.LessonEntity;
import vn.io.ductandev.course.request.LessonRequest;
import vn.io.ductandev.course.request.LessonRequestPatch;

public interface LessonService {

	List<LessonDTO> getListVideo();

	LessonByIdDTO getByID(int id);

	boolean addLesson(LessonRequest lessonRequest);

	LessonByIdDTO updateLesson(int id, LessonRequestPatch lessonRequestPatch);

	LessonDTO deleteLesson(int id);

}
