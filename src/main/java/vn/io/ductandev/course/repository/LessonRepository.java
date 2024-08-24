package vn.io.ductandev.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.io.ductandev.course.entity.LessonEntity;

import java.util.List;


@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Integer> {
    @Query("SELECT l FROM LessonEntity l WHERE l.isDelete = 0")
    List<LessonEntity> findAllByIsDeleteFalse();
}
