package vn.io.ductandev.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.io.ductandev.course.entity.RoleEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    @Query("SELECT r FROM RoleEntity r WHERE r.isDelete = 0")
    List<RoleEntity> findAllByIsDeleteFalse();

    @Query("SELECT r FROM RoleEntity r WHERE r.id = :id AND r.isDelete = 0")
    Optional<RoleEntity> findByIdAndIsDeleteFalse(@Param("id") int id);

}
