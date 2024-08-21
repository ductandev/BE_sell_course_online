package vn.io.ductandev.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.io.ductandev.course.entity.UserCourseEntity;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourseEntity, Integer> {
	
//	@Query(value = "SELECT pc.COURSE_ID, COUNT(pc.COURSE_ID) AS salesCount " +
//            "FROM COURSE.TB_PERSON_COURSE pc " +
//			"JOIN COURSE.TB_COURSE c ON pc.course_id = c.id " +
//            "GROUP BY pc.COURSE_ID " +
//            "ORDER BY salesCount DESC " +
//            "FETCH FIRST 3 ROWS ONLY", nativeQuery = true)
//		List<CourseTop3DTO> findTop3BestSellingCourses();
		

}
