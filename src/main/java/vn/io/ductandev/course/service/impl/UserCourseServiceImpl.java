package vn.io.ductandev.course.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.io.ductandev.course.dto.UserCourseDTO;
import vn.io.ductandev.course.entity.CourseEntity;
import vn.io.ductandev.course.entity.UserCourseEntity;
import vn.io.ductandev.course.entity.UserEntity;
import vn.io.ductandev.course.repository.CourseRepository;
import vn.io.ductandev.course.repository.UserCourseRepository;
import vn.io.ductandev.course.repository.UserRepository;
import vn.io.ductandev.course.service.UserCourseService;

@Service
public class UserCourseServiceImpl implements UserCourseService {

	@Autowired
	 private UserCourseRepository userCourseRepository;

	 @Autowired
	 private UserRepository userRepository;

	 @Autowired
	 private CourseRepository courseRepository;
	
	@Override
	public List<UserCourseDTO> getAllPersonCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addPersonCourse(UserCourseDTO userCourseDTO) {
		
		boolean isSuccess = false;
		
		try {
			
			UserCourseEntity userCourseEntity = new UserCourseEntity();
			
			UserEntity userEntity = userRepository.getById(userCourseDTO.getUser_id());
			CourseEntity courseEntity = courseRepository.getById(userCourseDTO.getCourse_id());

			userCourseEntity.setUser(userEntity);
			userCourseEntity.setCourse(courseEntity);
			userCourseEntity.setDateBuy(new Date());
			
			userCourseRepository.save(userCourseEntity);
			
			isSuccess = true;
			
			return isSuccess;
			
		} catch (Exception e) {
			return isSuccess;
		}
		
		
	}


}
