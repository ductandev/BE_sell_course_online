package vn.io.ductandev.course.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.io.ductandev.course.entity.RoleEntity;
import vn.io.ductandev.course.model.RoleDTO;
import vn.io.ductandev.course.repository.RoleRepository;
import vn.io.ductandev.course.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public Boolean addRole(RoleDTO roleDTO) {
		 try {
			 	RoleEntity roleEntity = convertToEntity(roleDTO);
	            roleRepository.save(roleEntity);
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	}

	@Override
	public Boolean update(int id, RoleDTO roleDTO) {
		 Optional<RoleEntity> existingRoleOpt = roleRepository.findById(id);
	        if (existingRoleOpt.isPresent()) {
	            RoleEntity existingRole = existingRoleOpt.get();
//	            existingRole.setName(roleEntity.getName());
	            roleRepository.save(existingRole);
	            return true;
	        } else {
	            return false;
	        }
	}

	private RoleDTO convertToDTO(RoleEntity roleEntity) {
	    RoleDTO roleDTO = new RoleDTO();
	    roleDTO.setId(roleEntity.getId());
	    roleDTO.setName(roleEntity.getName());
	    return roleDTO;
	}
	
	private RoleEntity convertToEntity(RoleDTO roleDTO) {
	    RoleEntity roleEntity = new RoleEntity();
	    roleEntity.setName(roleDTO.getName());
	    return roleEntity;
	}

	
//	@Override
//	public boolean addRole(String name) {
//		boolean isSuccess = false;
//		
//		try {
//			
//			RoleEntity roleEntity = new RoleEntity();
//			
//			roleEntity.setName(name);
//			
//			roleRepository.save(roleEntity);
//			
//			isSuccess = true;
//			
//		} catch (Exception e) {
//			System.out.println("Error add role : " + e);
//		}
//		
//		return isSuccess;
//	}
	
	

}
