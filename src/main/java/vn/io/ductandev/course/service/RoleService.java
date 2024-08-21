package vn.io.ductandev.course.service;

import vn.io.ductandev.course.dto.RoleDTO;
import vn.io.ductandev.course.request.RoleRequest;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getAllRoles();

    Boolean addRole(RoleRequest roleRequest);

    RoleDTO delete(int id);
}
