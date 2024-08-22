package vn.io.ductandev.course.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.io.ductandev.course.dto.CourseDTO;
import vn.io.ductandev.course.dto.ICourseTopSale;
import vn.io.ductandev.course.dto.LessonDTO;
import vn.io.ductandev.course.dto.RevenueRequestDTO;
import vn.io.ductandev.course.dto.RevenueResponseDTO;
import vn.io.ductandev.course.entity.CourseEntity;
import vn.io.ductandev.course.entity.LessonEntity;
import vn.io.ductandev.course.repository.CategoryRepository;
import vn.io.ductandev.course.repository.CourseRepository;
import vn.io.ductandev.course.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	

	@Override
	public List<CourseDTO> getListCourse() {
		 	List<CourseEntity> courseEntities = courseRepository.findAll();
		    List<CourseDTO> courseDTOs = new ArrayList<>();

		    for (CourseEntity courseEntity : courseEntities) {
		        CourseDTO courseDTO = new CourseDTO();
		        courseDTO.setId(courseEntity.getId());
		        courseDTO.setTitle(courseEntity.getTitle());
		        courseDTO.setPrice(courseEntity.getPrice());
		        courseDTO.setLecturer(courseEntity.getLecturer());
		        courseDTO.setCreateDate(courseEntity.getCreateDate());
		        courseDTO.setImage(courseEntity.getImage());
		        courseDTO.setDescription(courseEntity.getDescription());
		        courseDTO.setIsTopCourse(courseEntity.getIsTopCourse());
		        courseDTO.setIsFree(courseEntity.getIsFree());
		        courseDTO.setIsPublic(courseEntity.getIsPublic());
		        courseDTO.setIsDelete(courseEntity.getIsDelete());
		        
		        courseDTOs.add(courseDTO);
		        
		        
		        List<LessonDTO> listDtos = new ArrayList<>();
		        
		        for(LessonEntity lessonEntity : courseEntity.getLessons()) {
		        	
		        	LessonDTO lessonDTO = new LessonDTO();
		        	
		        	lessonDTO.setId(lessonEntity.getId());
		        	lessonDTO.setIsDelete(lessonEntity.getIsDelete());
		        	lessonDTO.setIsSuccess(lessonEntity.getIsSuccess());
		        	lessonDTO.setName(lessonEntity.getName());
		        	lessonDTO.setVideoUrl(lessonEntity.getVideoUrl());
		        	
		        	listDtos.add(lessonDTO);
		        }
		        	
		        
		        courseDTO.setLessonDTOs(listDtos);
		        
		        
		    }

		    return courseDTOs;
	}

	@Override
	public boolean addCourse(CourseDTO courseDTO) {
		boolean isSuccess = false;
		 	try {
		 		CourseEntity course = new CourseEntity();
		        course.setTitle(courseDTO.getTitle());
		        course.setPrice(courseDTO.getPrice());
		        course.setLecturer(courseDTO.getLecturer());
		        course.setCreateDate(courseDTO.getCreateDate());
		        course.setImage(courseDTO.getImage());
		        course.setDescription(courseDTO.getDescription());

		        
		    
			    courseRepository.save(course);
			  
		        
			return isSuccess;
			} catch (Exception e) {
				e.printStackTrace();
			}
		 	return isSuccess;
	}

	@Override
	public CourseDTO getCourseById(int id) {
		
		try {
			CourseEntity courseEntity = courseRepository.getById(id);
			
			CourseDTO courseDTO = new CourseDTO();
			
			 	courseDTO.setId(courseEntity.getId());
		        courseDTO.setTitle(courseEntity.getTitle());
		        courseDTO.setPrice(courseEntity.getPrice());
		        courseDTO.setLecturer(courseEntity.getLecturer());
		        courseDTO.setCreateDate(courseEntity.getCreateDate());
		        courseDTO.setImage(courseEntity.getImage());
		        courseDTO.setDescription(courseEntity.getDescription());
		        courseDTO.setIsTopCourse(courseEntity.getIsTopCourse());
		        courseDTO.setIsFree(courseEntity.getIsFree());
		        courseDTO.setIsPublic(courseEntity.getIsPublic());
		        courseDTO.setIsDelete(courseEntity.getIsDelete());
		        
		        List<LessonDTO> listDtos = new ArrayList<>();
		        
		        for(LessonEntity lessonEntity : courseEntity.getLessons()) {
		        	
		        	LessonDTO lessonDTO = new LessonDTO();
		        	
		        	lessonDTO.setId(lessonEntity.getId());
		        	lessonDTO.setIsDelete(lessonEntity.getIsDelete());
		        	lessonDTO.setIsSuccess(lessonEntity.getIsSuccess());
		        	lessonDTO.setName(lessonEntity.getName());
		        	lessonDTO.setVideoUrl(lessonEntity.getVideoUrl());
		        	
		        	listDtos.add(lessonDTO);
		        }
		        	
		        
		        courseDTO.setLessonDTOs(listDtos);
			
			return courseDTO;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean updateCourse(int id, CourseDTO courseDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCourse(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ICourseTopSale> getTop5BestSellingBooks() {
		List<ICourseTopSale> list = courseRepository.findTop3BestSellingCourses();
		return list;
	}

	@Override
	public List<RevenueResponseDTO> getRevenueByMonthAndYear(RevenueRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		 List<Object[]> results = courseRepository.calculateRevenueByMonthAndYear(requestDTO.getMonth(), requestDTO.getYear());
	        return results.stream().map(result -> {
	            RevenueResponseDTO dto = new RevenueResponseDTO();
	            dto.setMonthYear((String) result[0]);
	            dto.setTotalRevenue(((Number) result[1]).floatValue());
	            return dto;
	        }).collect(Collectors.toList());
	}

	

}
