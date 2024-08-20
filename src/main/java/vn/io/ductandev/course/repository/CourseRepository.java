package vn.io.ductandev.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.io.ductandev.course.dto.RevenueResponseDTO;
import vn.io.ductandev.course.dto.ICourseTopSale;
import vn.io.ductandev.course.entity.CourseEntity;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {

	 @Query(value = "SELECT c.id AS courseId, c.title AS courseName, COUNT(pc.course_id) AS salesCount "
	 		+ "             FROM COURSE.TB_PERSON_COURSE pc "
	 		+ "             JOIN COURSE.TB_COURSE c ON pc.course_id = c.id "
	 		+ "             GROUP BY c.id, c.title "
	 		+ "             ORDER BY salesCount DESC "
	 		+ "             FETCH FIRST 3 ROWS ONLY", nativeQuery = true)
		List<ICourseTopSale> findTop3BestSellingCourses();

	 @Query(value = "SELECT TO_CHAR(pc.DATEBUY, 'YYYY-MM') AS monthYear, SUM(c.price) AS totalRevenue " +
             "FROM COURSE.TB_PERSON_COURSE pc " +
             "JOIN COURSE.TB_COURSE c ON pc.course_id = c.id " +
             "WHERE TO_CHAR(pc.DATEBUY, 'MM') = :month AND TO_CHAR(pc.DATEBUY, 'YYYY') = :year " +
             "GROUP BY TO_CHAR(pc.DATEBUY, 'YYYY-MM') " +
             "ORDER BY monthYear", nativeQuery = true)
	 List<Object[]> calculateRevenueByMonthAndYear(@Param("month") String month, @Param("year") String year);
	
}
