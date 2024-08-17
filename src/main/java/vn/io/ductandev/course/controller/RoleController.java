package vn.io.ductandev.course.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.io.ductandev.course.dto.CourseDTO;
import vn.io.ductandev.course.dto.RoleDTO;
import vn.io.ductandev.course.entity.RoleEntity;
import vn.io.ductandev.course.payload.ResponData;
import vn.io.ductandev.course.response.ResponseDTO;
import vn.io.ductandev.course.service.RoleService;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@PostMapping
	public ResponseEntity<?> addRole(@RequestBody RoleDTO roleDTO) {

	    Boolean isAdd = roleService.addRole(roleDTO);
	    
	    if (isAdd) {
        	ResponseDTO<RoleDTO> response = new ResponseDTO<>(
                    "Thành công !",
                    HttpStatus.OK.value(),
                    (RoleDTO) roleDTO,
                    new Date()
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to add role", HttpStatus.BAD_REQUEST);
        }
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateRole(@PathVariable int id, @RequestBody RoleDTO roleDTO) {
	    
	    Boolean isUpdate = roleService.update(id, roleDTO);

	    
	    if (isUpdate) {
        	ResponseDTO<RoleDTO> response = new ResponseDTO<>(
                    "Thành công !",
                    HttpStatus.OK.value(),
                    (RoleDTO) roleDTO,
                    new Date()
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update role", HttpStatus.BAD_REQUEST);
        }
	}

}
