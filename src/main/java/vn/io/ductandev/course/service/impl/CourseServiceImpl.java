package vn.io.ductandev.course.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.io.ductandev.course.dto.*;
import vn.io.ductandev.course.entity.CategoryEntity;
import vn.io.ductandev.course.entity.CourseEntity;
import vn.io.ductandev.course.entity.LessonEntity;
import vn.io.ductandev.course.repository.CategoryRepository;
import vn.io.ductandev.course.repository.CourseRepository;
import vn.io.ductandev.course.request.CourseRequest;
import vn.io.ductandev.course.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CategoryRepository categoryRepository;


    // ================================================
    //               	GET ALL COURSE
    // ================================================
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

            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(courseEntity.getCategory().getId());
            categoryDTO.setName(courseEntity.getCategory().getName());
            categoryDTO.setIsDelete(courseEntity.getCategory().getIsDelete());

            courseDTO.setCategoryDTO(categoryDTO);

            courseDTOs.add(courseDTO);

            List<LessonDTO> listDtos = new ArrayList<>();

            for (LessonEntity lessonEntity : courseEntity.getLessons()) {
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

    // ================================================
    //              GET COURSE BY ID
    // ================================================
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

            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(courseEntity.getCategory().getId());
            categoryDTO.setName(courseEntity.getCategory().getName());
            categoryDTO.setIsDelete(courseEntity.getCategory().getIsDelete());

            courseDTO.setCategoryDTO(categoryDTO);

            List<LessonDTO> listDtos = new ArrayList<>();

            for (LessonEntity lessonEntity : courseEntity.getLessons()) {
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

    // ================================================
    //               	CREATE COURSE
    // ================================================
    @Override
    public CourseEntity addCourse(CourseRequest courseRequest) {
        try {
            CourseEntity course = new CourseEntity();
            course.setTitle(courseRequest.title());
            course.setPrice(courseRequest.price());
            course.setLecturer(courseRequest.lecturer());
            course.setImage(courseRequest.image());
            course.setDescription(courseRequest.description());

			CategoryEntity categoryEntity = categoryRepository.getById(courseRequest.category_id());

			course.setCategory(categoryEntity);
			course.setCreateDate(new Date());
            courseRepository.save(course);

            return course;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ================================================
    //               	UPDATE COURSE
    // ================================================
    @Override
    public boolean updateCourse(int id, CourseDTO courseDTO) {
        // TODO Auto-generated method stub
        return false;
    }

    // ================================================
    //               	DELETE COURSE
    // ================================================
    @Override
    public boolean deleteCourse(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    // ================================================
    //          GET TOP 5 COURSE BEST SELLING
    // ================================================
    @Override
    public List<ICourseTopSale> getTop5BestSellingBooks() {
        List<ICourseTopSale> list = courseRepository.findTop3BestSellingCourses();
        return list;
    }

    // ================================================
    //          GET REVENUE BY MONTH AND YEAR
    // ================================================
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
