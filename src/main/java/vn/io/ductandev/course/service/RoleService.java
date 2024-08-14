package vn.io.ductandev.course.service;

public interface RoleService {
	
	boolean addRole(String name);
	boolean update(Integer id, String name);
}
