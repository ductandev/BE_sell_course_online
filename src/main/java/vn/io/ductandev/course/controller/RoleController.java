package vn.io.ductandev.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.io.ductandev.course.payload.ResponData;
import vn.io.ductandev.course.service.impl.RoleServiceImpl;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
	
	@Autowired
	RoleServiceImpl roleServiceImpl;
	
	@PostMapping("/add")
	public ResponseEntity<?> addRole(@RequestParam String name){
		ResponData responData = new ResponData();
		
		Boolean isSuccess = roleServiceImpl.addRole(name);
		
		responData.setData(isSuccess);
		
		return new ResponseEntity<>(responData, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> addRole(@RequestParam int id, @RequestParam String name){
		ResponData responData = new ResponData();
		
		Boolean isSuccess = roleServiceImpl.update(id, name);
		
		responData.setData(isSuccess);
		
		return new ResponseEntity<>(responData, HttpStatus.OK);
	}

}
