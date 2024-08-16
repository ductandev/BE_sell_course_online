package vn.io.ductandev.course.service;

import vn.io.ductandev.course.model.RoleDTO;

public interface RoleService {
	
	 Boolean addRole(RoleDTO roleDTO);
	 Boolean update(int id, RoleDTO roleDTO);
}
