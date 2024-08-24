package vn.io.ductandev.course.service;

import java.util.List;

import vn.io.ductandev.course.dto.CourseDTO;
import vn.io.ductandev.course.dto.ICourseTopSale;
import vn.io.ductandev.course.dto.RevenueRequestDTO;
import vn.io.ductandev.course.dto.RevenueResponseDTO;
import vn.io.ductandev.course.entity.CourseEntity;
import vn.io.ductandev.course.request.CourseRequest;
import vn.io.ductandev.course.request.CourseRequestPatch;

public interface CourseService {

	List<CourseDTO> getListCourse(String searchByName, int page, int limit, Integer categoryID);

	CourseEntity addCourse(CourseRequest courseRequest);

	CourseDTO getCourseById(int id);

	CourseDTO updateCourseById(int id, CourseRequestPatch courseRequestPatch);

	CourseDTO deleteCourseById(int id);

	List<ICourseTopSale> getTop5BestSellingBooks();

	List<RevenueResponseDTO> getRevenueByMonthAndYear(RevenueRequestDTO courseSalesDTO);
}
