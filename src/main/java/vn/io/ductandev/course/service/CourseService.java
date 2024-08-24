package vn.io.ductandev.course.service;

import java.util.List;

import org.springframework.data.domain.Page;

import vn.io.ductandev.course.dto.CourseDTO;
import vn.io.ductandev.course.dto.ICourseTopSale;
import vn.io.ductandev.course.dto.RevenueRequestDTO;
import vn.io.ductandev.course.dto.RevenueResponseDTO;
import vn.io.ductandev.course.entity.CourseEntity;
import vn.io.ductandev.course.request.CourseRequest;
import vn.io.ductandev.course.request.CourseRequestUpdate;

public interface CourseService {

	List<CourseDTO> getListCourse();
	
	CourseEntity addCourse(CourseRequest courseRequest);
	
	CourseDTO getCourseById(int id);
	
	CourseRequestUpdate updateCourse(int id, CourseRequestUpdate courseRequestUpdate);
	
	CourseDTO deleteCourse(int id);
	
	List<ICourseTopSale> getTop5BestSellingBooks();
	
	List<RevenueResponseDTO> getRevenueByMonthAndYear(RevenueRequestDTO courseSalesDTO);
	
	Page<CourseDTO> getCoursesByTitleAndCategory(String title, int categoryId, int page, int limit);
}
