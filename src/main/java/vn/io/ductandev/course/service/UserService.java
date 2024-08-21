package vn.io.ductandev.course.service;

import java.util.List;

import vn.io.ductandev.course.dto.UserDTO;
import vn.io.ductandev.course.request.UserRequest;

public interface UserService {
	
	List<UserDTO> getListUser();
	
	boolean addUser(UserRequest userRequest);
	
	UserDTO getbyID(int id);
	
	boolean updateUser(int id, UserDTO userDTO);
	
	boolean deleteUser(int id);
	
}
