package vn.io.ductandev.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.io.ductandev.course.dto.ICourseTopSale;
import vn.io.ductandev.course.entity.CourseEntity;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {

//		@Query(value =  "SELECT new vn.oi.ductandev.course.dto.CourseTop3DTO(c.id, c.title, COUNT(pc.course.id)) " +
//	           "FROM PersonCourseEntity pc " +
//	           "JOIN pc.course c " +
//	           "GROUP BY c.id, c.title " +
//	           "ORDER BY COUNT(pc.course.id) DESC", nativeQuery = true)
	 @Query(value = "SELECT c.id AS courseId, c.title AS courseName, COUNT(pc.course_id) AS salesCount "
	 		+ "             FROM COURSE.TB_PERSON_COURSE pc "
	 		+ "             JOIN COURSE.TB_COURSE c ON pc.course_id = c.id "
	 		+ "             GROUP BY c.id, c.title "
	 		+ "             ORDER BY salesCount DESC "
	 		+ "             FETCH FIRST 3 ROWS ONLY", nativeQuery = true)
		List<ICourseTopSale> findTop3BestSellingCourses();

	
	
}
