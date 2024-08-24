package vn.io.ductandev.course.config;

import vn.io.ductandev.course.dto.CourseDTO;
import vn.io.ductandev.course.entity.CourseEntity;

public class CourseMapper {
	 public static CourseDTO toDTO(CourseEntity courseEntity) {
	        CourseDTO courseDTO = new CourseDTO();
	        courseDTO.setId(courseEntity.getId());
	        courseDTO.setTitle(courseEntity.getTitle());
	        courseDTO.setPrice(courseEntity.getPrice());
	        courseDTO.setLecturer(courseEntity.getLecturer());
	        courseDTO.setImage(courseEntity.getImage());
	        courseDTO.setDescription(courseEntity.getDescription());
	        courseDTO.setCreateDate(courseEntity.getCreateDate());
	        courseDTO.setIsTopCourse(courseEntity.getIsTopCourse());
	        courseDTO.setIsFree(courseEntity.getIsFree());
	        courseDTO.setIsPublic(courseEntity.getIsPublic());
	        courseDTO.setIsDelete(courseEntity.getIsDelete());
	        return courseDTO;
	    }
}
