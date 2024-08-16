package vn.io.ductandev.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.io.ductandev.course.entity.RoleEntity;
import vn.io.ductandev.course.model.RoleDTO;
import vn.io.ductandev.course.payload.ResponData;
import vn.io.ductandev.course.service.RoleService;
import vn.io.ductandev.course.service.impl.RoleServiceImpl;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@PostMapping
	public ResponseEntity<?> addRole(@RequestBody RoleDTO roleDTO) {
	    ResponData responData = new ResponData();

	    Boolean isSuccess = roleService.addRole(roleDTO);
	    
	    responData.setData(isSuccess);
	    
	    return new ResponseEntity<>(responData, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateRole(@PathVariable int id, @RequestBody RoleDTO roleDTO) {
	    
//	    RoleEntity roleEntity = convertToEntity(roleDTO);
	    Boolean isSuccess = roleService.update(id, roleDTO);

	    
	    if (isSuccess) {
	          return new ResponseEntity<>("Role updated successfully", HttpStatus.OK);
	      } else {        
	          return new ResponseEntity<>("Failed to update role", HttpStatus.BAD_REQUEST);
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
	    roleEntity.setId(roleDTO.getId());
	    roleEntity.setName(roleDTO.getName());
	    return roleEntity;
	}


}
