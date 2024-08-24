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

	// Truy vấn tùy chỉnh để tìm kiếm, lọc, và sắp xếp
	@Query("SELECT c FROM CourseEntity c WHERE c.isDelete = 0 " +
			"AND (:searchByName IS NULL OR LOWER(c.title) LIKE LOWER(CONCAT('%', :searchByName, '%'))) " +
			"AND (:categoryID IS NULL OR c.category.id = :categoryID) " +
			"ORDER BY c.id ASC")
	List<CourseEntity> findCourses(@Param("searchByName") String searchByName,
								   @Param("categoryID") Integer categoryID);

	 @Query(value = "SELECT c.id AS courseId, c.title AS courseName, COUNT(pc.course_id) AS salesCount"
	 		+ " FROM COURSE.TB_USER_COURSE pc"
	 		+ " JOIN COURSE.TB_COURSE c ON pc.course_id = c.id"
	 		+ " GROUP BY c.id, c.title"
	 		+ " ORDER BY salesCount DESC"
	 		+ " FETCH FIRST 5 ROWS ONLY", nativeQuery = true)
		List<ICourseTopSale> findTop3BestSellingCourses();

	 @Query(value = "SELECT TO_CHAR(pc.DATE_BUY, 'YYYY-MM') AS monthYear, SUM(c.price) AS totalRevenue " +
             "FROM COURSE.TB_USER_COURSE pc " +
             "JOIN COURSE.TB_COURSE c ON pc.course_id = c.id " +
             "WHERE TO_CHAR(pc.DATE_BUY, 'MM') = :month AND TO_CHAR(pc.DATE_BUY, 'YYYY') = :year " +
             "GROUP BY TO_CHAR(pc.DATE_BUY, 'YYYY-MM') " +
             "ORDER BY monthYear", nativeQuery = true)
	 List<Object[]> calculateRevenueByMonthAndYear(@Param("month") String month, @Param("year") String year);
	
}
