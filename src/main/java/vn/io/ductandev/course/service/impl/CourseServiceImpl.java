package vn.io.ductandev.course.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.io.ductandev.course.entity.CategoryEntity;
import vn.io.ductandev.course.entity.CourseEntity;
import vn.io.ductandev.course.dto.CategoryDTO;
import vn.io.ductandev.course.dto.CourseDTO;
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

		        // If you need to map the category
		        CategoryDTO categoryDTO = new CategoryDTO();
		        categoryDTO.setId(courseEntity.getCategory().getId());
		        categoryDTO.setName(courseEntity.getCategory().getName());
		        courseDTO.setCategory(categoryDTO);

		        courseDTOs.add(courseDTO);
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

		        CategoryEntity category = categoryRepository.getById(courseDTO.getCategory().getId());
		        
		        if(category == null) {
		        	isSuccess = false;
		        }else {
		        	course.setCategory(category);
			        courseRepository.save(course);
			        isSuccess = true;
		        }
		        
			return isSuccess;
			} catch (Exception e) {
				e.printStackTrace();
			}
		 	return isSuccess;
	}

	@Override
	public boolean getCourseById(int id) {
		// TODO Auto-generated method stub
		return false;
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

}
