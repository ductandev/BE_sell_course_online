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
import vn.io.ductandev.course.request.CourseRequestPatch;
import vn.io.ductandev.course.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CategoryRepository categoryRepository;


    // ================================================
    //             GET ALL COURSE PAGINATION
    // ================================================
    @Override
    public List<CourseDTO> getListCourse(String searchByName, int page, int limit, Integer categoryID) {

        // Lấy danh sách các khóa học chưa bị xóa, tìm kiếm, lọc và sắp xếp theo tên
        List<CourseEntity> courseEntities = courseRepository.findCourses(searchByName, categoryID);

        // Phân trang
        int start = (page - 1) * limit;
        int end = Math.min((start + limit), courseEntities.size());
        List<CourseEntity> paginatedCourses = courseEntities.subList(start, end);

        // Chuyển đổi danh sách CourseEntity thành CourseDTO
        List<CourseDTO> courseDTOs = paginatedCourses.stream().map(courseEntity -> {
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

            // Thiết lập CategoryDTO
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(courseEntity.getCategory().getId());
            categoryDTO.setName(courseEntity.getCategory().getName());
            categoryDTO.setIsDelete(courseEntity.getCategory().getIsDelete());
            courseDTO.setCategoryDTO(categoryDTO);

            // Thiết lập danh sách LessonDTOs
            List<LessonDTO> lessonDTOs = courseEntity.getLessons().stream().map(lessonEntity -> {
                LessonDTO lessonDTO = new LessonDTO();
                lessonDTO.setId(lessonEntity.getId());
                lessonDTO.setIsDelete(lessonEntity.getIsDelete());
                lessonDTO.setIsSuccess(lessonEntity.getIsSuccess());
                lessonDTO.setName(lessonEntity.getName());
                lessonDTO.setVideoUrl(lessonEntity.getVideoUrl());
                return lessonDTO;
            }).collect(Collectors.toList());

            courseDTO.setLessonDTOs(lessonDTOs);

            return courseDTO;
        }).collect(Collectors.toList());

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
    //              UPDATE COURSE BY ID
    // ================================================
    @Override
    public CourseDTO updateCourseById(int id, CourseRequestPatch courseRequestPatch) {
        try {
            CourseEntity courseEntity = courseRepository.getById(id);
            courseEntity.setTitle(courseRequestPatch.title());
            courseEntity.setPrice(courseRequestPatch.price());
            courseEntity.setLecturer(courseRequestPatch.lecturer());
            courseEntity.setImage(courseRequestPatch.image());
            courseEntity.setDescription(courseRequestPatch.description());
            //courseEntity.setCreateDate(courseRequestPatch.createDate());
            //courseEntity.setIsTopCourse(courseRequestPatch.isTopCourse());
            courseEntity.setIsFree(courseRequestPatch.isFree());

            CategoryEntity categoryEntity = categoryRepository.getById(courseRequestPatch.categoryId());
            courseEntity.setCategory(categoryEntity);

            courseEntity.setIsPublic(courseRequestPatch.isPublic());
            //courseEntity.setIsDelete(courseRequestPatch.isDelete());

            courseRepository.save(courseEntity);

            // Trả Respon data dưới dạng 1 DTO
            CourseDTO courseDTO = new CourseDTO();

            courseDTO.setId(courseEntity.getId());
            courseDTO.setTitle(courseRequestPatch.title());
            courseDTO.setPrice(courseRequestPatch.price());
            courseDTO.setLecturer(courseRequestPatch.lecturer());
            courseDTO.setImage(courseRequestPatch.image());
            courseDTO.setDescription(courseRequestPatch.description());
            //courseDTO.setCreateDate(courseRequestPatch.createDate());
            //courseDTO.setIsTopCourse(courseRequestPatch.isTopCourse());
            courseDTO.setIsFree(courseRequestPatch.isFree());
            courseDTO.setIsPublic(courseRequestPatch.isPublic());

            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(categoryEntity.getId());
            categoryDTO.setName(categoryEntity.getName());
            categoryDTO.setIsDelete(categoryEntity.getIsDelete());

            courseDTO.setCategoryDTO(categoryDTO);
            //courseDTO.setIsDelete(courseRequestPatch.isDelete());

            return courseDTO;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    // ================================================
    //               	DELETE COURSE
    // ================================================
    @Override
    public CourseDTO deleteCourseById(int id) {
        try {
            CourseEntity courseEntity = courseRepository.getById(id);
            courseEntity.setIsDelete(1);
            courseRepository.save(courseEntity);

            // Trả Respon data dưới dạng 1 DTO
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

            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(courseEntity.getCategory().getId());
            categoryDTO.setName(courseEntity.getCategory().getName());
            categoryDTO.setIsDelete(courseEntity.getCategory().getIsDelete());

            courseDTO.setCategoryDTO(categoryDTO);
            courseDTO.setIsDelete(courseEntity.getIsDelete());

            return courseDTO;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
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
