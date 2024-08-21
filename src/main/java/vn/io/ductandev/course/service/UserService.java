package vn.io.ductandev.course.service;

import java.util.List;

import vn.io.ductandev.course.dto.UserDTO;

public interface UserService {
	
	List<UserDTO> getListPerson();
	
	boolean addPerson(UserDTO userDTO);
	
	UserDTO getbyID(int id);
	
	boolean updatePerson(int id, UserDTO userDTO);
	
	boolean deletePerson(int id);
	
}
