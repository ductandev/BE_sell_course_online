package vn.io.ductandev.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.io.ductandev.course.dto.RoleDTO;
import vn.io.ductandev.course.entity.RoleEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    @Query(value = "SELECT tr.id, tr.name, tr.DESCRIPTION, tr.IS_DELETE FROM COURSE.TB_ROLE tr WHERE IS_DELETE = 0 ORDER BY ID ASC", nativeQuery = true)
    List<RoleEntity> findAllByIsDeleteFalse();
    Optional<RoleEntity> findByIdAndIsDeleteFalse(int id);
}
