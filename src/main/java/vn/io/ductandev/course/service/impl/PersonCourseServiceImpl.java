package vn.io.ductandev.course.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.io.ductandev.course.dto.PersonCourseDTO;
import vn.io.ductandev.course.entity.CourseEntity;
import vn.io.ductandev.course.entity.PersonCourseEntity;
import vn.io.ductandev.course.entity.PersonEntity;
import vn.io.ductandev.course.repository.CourseRepository;
import vn.io.ductandev.course.repository.PersonCourseRepository;
import vn.io.ductandev.course.repository.PersonRepository;
import vn.io.ductandev.course.service.PersonCourseService;

@Service
public class PersonCourseServiceImpl implements PersonCourseService {

	@Autowired
	 private PersonCourseRepository personCourseRepository;

	 @Autowired
	 private PersonRepository personRepository;

	 @Autowired
	 private CourseRepository courseRepository;
	
	@Override
	public List<PersonCourseDTO> getAllPersonCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addPersonCourse(PersonCourseDTO personCourseDTO) {
		
		boolean isSuccess = false;
		
		try {
			
			PersonCourseEntity personCourseEntity = new PersonCourseEntity();
			
			PersonEntity personEntity = personRepository.findById(personCourseDTO.getPerson().getId()).orElse(null);
			CourseEntity courseEntity = courseRepository.findById(personCourseDTO.getCourse().getId()).orElse(null);

			personCourseEntity.setPerson(personEntity);
			personCourseEntity.setCourse(courseEntity);
			personCourseEntity.setDateBuy(new Date());
			
			personCourseRepository.save(personCourseEntity);
			
			isSuccess = true;
			
			return isSuccess;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isSuccess;
	}


}
