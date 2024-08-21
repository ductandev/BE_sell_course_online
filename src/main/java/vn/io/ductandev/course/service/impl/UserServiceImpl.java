package vn.io.ductandev.course.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.io.ductandev.course.config.Config;
import vn.io.ductandev.course.config.Mapper;
import vn.io.ductandev.course.entity.UserEntity;
import vn.io.ductandev.course.entity.RoleEntity;
import vn.io.ductandev.course.dto.UserDTO;
import vn.io.ductandev.course.dto.RoleDTO;
import vn.io.ductandev.course.repository.UserRepository;
import vn.io.ductandev.course.repository.RoleRepository;
import vn.io.ductandev.course.request.UserRequest;
import vn.io.ductandev.course.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	Config appConfig;
	
	@Autowired
	Mapper<UserDTO> personMapper;


	@Override
	public List<UserDTO> getListUser() {

		List<UserEntity> listEntities = userRepository.findAll();
		
		List<UserDTO> listDtos = new ArrayList<>();
		
		for(UserEntity p : listEntities) {
			
			UserDTO userDTO = new UserDTO();
			
//			userDTO.setId(p.getId());
			userDTO.setUsername(p.getUsername());
			userDTO.setPassword(p.getPassword());
			
			
			RoleEntity role = roleRepository.getById(p.getRole().getId());
			
			RoleDTO roleDTO = new RoleDTO();
			
			roleDTO.setId(role.getId());
			roleDTO.setName(role.getName());
			
			userDTO.setRole(p.getRole().getId());
				
			listDtos.add(userDTO);
			
		}
		
		return listDtos;
	}

	@Override
	public boolean addUser(UserRequest userRequest) {
		
		boolean isSuccess = false;
		
		try {
			RoleEntity r = roleRepository.getById(2);

			UserEntity u = new UserEntity();
			
			u.setUsername(userRequest.username());
			u.setPassword(userRequest.password());
			u.setEmail(userRequest.email());
			u.setAvatar(userRequest.avatar());
			
			u.setRole(r);

			userRepository.save(u);

			isSuccess = true;
			return isSuccess;
		} catch (Exception e) {
			return isSuccess;
		}

		
	}

	@Override
	public boolean deleteUser(int id) {
		
		Boolean isSuccess = false;
		
		try {
		 UserEntity user = userRepository.getById(id);

         user.setIsDelete(1);

         userRepository.save(user);
         
         isSuccess = true;
         
         return isSuccess;
     } catch (Exception e) {
         return isSuccess;
     }
	}


	@Override
	public UserDTO getbyID(int id) {
		UserEntity p = userRepository.getById(id);
		
		UserDTO userDTO = new UserDTO();
		
//		userDTO.setId(p.getId());
		userDTO.setUsername(p.getUsername());
		userDTO.setPassword(p.getPassword());
//		userDTO.setIsDelete(p.getIsDelete());
		
		return userDTO;
	}

	@Override
	public boolean updateUser(int id, UserDTO userDTO) {
		return false;
	}


}
