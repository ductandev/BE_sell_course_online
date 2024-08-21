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
	public List<UserDTO> getListPerson() {
		
		List<UserEntity> listEntities = userRepository.findAll();
		
		List<UserDTO> listDtos = new ArrayList<>();
		
		for(UserEntity p : listEntities) {
			
			UserDTO userDTO = new UserDTO();
			
			userDTO.setId(p.getId());
			userDTO.setUsername(p.getUsername());
			userDTO.setPassword(p.getPassword());
			
			RoleEntity role = roleRepository.getById(p.getRole().getId());
			
			RoleDTO roleDTO = new RoleDTO();
			
			roleDTO.setId(role.getId());
			roleDTO.setName(role.getName());
			
			userDTO.setRole(roleDTO);
				
			listDtos.add(userDTO);
			
		}
		
		return listDtos;
	}

	@Override
	public boolean addPerson(UserDTO userDTO) {
		
		boolean isSuccess = false;
		
		try {
			RoleEntity r = roleRepository.getById(2);
			
			UserEntity p = personMapper.convert(userDTO, UserEntity.class);
			
			p.setRole(r);
			
			userRepository.save(p);
			
			isSuccess = true;
			
			return isSuccess;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
		
	}

	@Override
	public boolean updatePerson(int id, UserDTO userDTO) {
		
		Boolean isSuccess = false;
		
		try {
		 UserEntity user = userRepository.getById(id);

         user.setUsername(userDTO.getUsername());
         user.setPassword(userDTO.getPassword());
         user.setIsDelete(userDTO.getIsDelete());

         userRepository.save(user);
         
         isSuccess = true;
         
         return isSuccess;
     } catch (Exception e) {
         return isSuccess;
     }
	}

	@Override
	public boolean deletePerson(int id) {
		Boolean isSuccess = false;
		
		UserEntity user = userRepository.getById(id);
		 
		if(user != null) {
			 user.setIsDelete(1);
			 userRepository.save(user);
			 isSuccess = true;
			 return isSuccess;
		}else {
			return isSuccess;
		}
			
		
	}

	@Override
	public UserDTO getbyID(int id) {
		UserEntity p = userRepository.getById(id);
		
		UserDTO userDTO = new UserDTO();
		
		userDTO.setId(p.getId());
		userDTO.setUsername(p.getUsername());
		userDTO.setPassword(p.getPassword());
		userDTO.setIsDelete(p.getIsDelete());
		
		return userDTO;
	}
	
	
	
}
