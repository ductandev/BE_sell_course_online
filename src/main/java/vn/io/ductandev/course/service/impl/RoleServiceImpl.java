package vn.io.ductandev.course.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.io.ductandev.course.entity.RoleEntity;
import vn.io.ductandev.course.repository.RoleRepository;
import vn.io.ductandev.course.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public boolean addRole(String name) {
		boolean isSuccess = false;
		
		try {
			
			RoleEntity roleEntity = new RoleEntity();
			
			roleEntity.setName(name);
			
			roleRepository.save(roleEntity);
			
			isSuccess = true;
			
		} catch (Exception e) {
			System.out.println("Error add role : " + e);
		}
		
		return isSuccess;
	}

	@Override
	public boolean update(Integer id, String name) {
		boolean isSuccess = false;
		
		RoleEntity roleEntity = roleRepository.getById(id);
			
		if(roleEntity != null) {
			roleEntity.setName(name);
			roleRepository.save(roleEntity);
				
			isSuccess = true;
		}
			
		return isSuccess;
	}

}
