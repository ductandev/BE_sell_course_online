package vn.io.ductandev.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.io.ductandev.course.entity.PersonCourseEntity;
import vn.io.ductandev.course.entity.PersonCourseId;

@Repository
public interface PersonCourseRepository extends JpaRepository<PersonCourseEntity, PersonCourseId> {

}
