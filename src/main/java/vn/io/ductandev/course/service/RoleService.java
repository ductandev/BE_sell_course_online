package vn.io.ductandev.course.service;

import vn.io.ductandev.course.dto.RoleDTO;
import vn.io.ductandev.course.entity.RoleEntity;
import vn.io.ductandev.course.request.RoleRequest;

import java.util.Optional;

public interface RoleService {
    Iterable<RoleDTO> getAllRoles();
    Optional<RoleEntity> getRoleById(int id);
    Boolean addRole(RoleRequest roleRequest);
    RoleDTO delete(int id);
}
