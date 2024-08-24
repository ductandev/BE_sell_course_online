package vn.io.ductandev.course.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.io.ductandev.course.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

//	 @Query(value = "SELECT c FROM CategoryEntity c WHERE " +
//	            "(:name IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
//	            "c.isDelete = 0",
//	            countQuery = "SELECT COUNT(c) FROM CategoryEntity c WHERE " +
//	                    "(:name IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
//	                    "c.isDelete = 0")
//	  Page<CategoryEntity> findCategoriesWithFilters(@Param("name") String name, Pageable pageable);

	 @Query("SELECT c FROM CategoryEntity c WHERE (:nameCourse IS NULL OR c.name LIKE %:nameCourse%) AND (:categoryID IS NULL OR c.id = :categoryID) AND c.isDelete = 0")
	    Page<CategoryEntity> findByNameCourseAndCategoryID(@Param("nameCourse") String nameCourse,@Param("categoryID") Integer categoryID, Pageable pageable);


}
