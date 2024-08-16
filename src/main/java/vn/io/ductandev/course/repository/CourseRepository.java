package vn.io.ductandev.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.io.ductandev.course.entity.CourseEntity;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {

}
