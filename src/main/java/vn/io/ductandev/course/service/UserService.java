package vn.io.ductandev.course.service;

import java.util.List;

import vn.io.ductandev.course.dto.UserByIdDTO;
import vn.io.ductandev.course.dto.UserDTO;
import vn.io.ductandev.course.entity.UserEntity;
import vn.io.ductandev.course.request.UserRequest;
import vn.io.ductandev.course.request.UserRequestPatch;

public interface UserService {
	
	List<UserDTO> getListUser();
	
	UserByIdDTO getbyID(int id);
	
	UserEntity updateUser(int id, UserRequestPatch userRequestPatcht);
	
	boolean deleteUser(int id);
	
}
