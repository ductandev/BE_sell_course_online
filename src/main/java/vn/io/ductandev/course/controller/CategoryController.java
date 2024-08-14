package vn.io.ductandev.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.io.ductandev.course.payload.ResponData;
import vn.io.ductandev.course.service.impl.CategoryServiceImpl;

@RestController
@RequestMapping("/api/categorys")
public class CategoryController {
	
	@Autowired
	CategoryServiceImpl categoryServiceImpl;
	
//	@PostMapping("/add")
//	public ResponseEntity<?> getAllCategory(){
//		ResponData responData = new ResponData();
//		try {
//			
////			ResponData responData = new ResponData();
//			
////			Boolean isSuccess = categoryServiceImpl.getListCategory();
//			
////			responData.setData(isSuccess);
//			
//			return new ResponseEntity<>(responData, HttpStatus.OK);
//			
//		} catch (Exception e) {
//			Boolean isFalse = false;
//			responData.setData(isFalse);
//			
//	        return new ResponseEntity<>(responData, HttpStatus.BAD_REQUEST);
//		}
//		
//	}
//	
//	
//	@PostMapping("/add")
//	public ResponseEntity<?> addCategory(@RequestParam String name){
//		ResponData responData = new ResponData();
//		try {
//			
////			ResponData responData = new ResponData();
//			
//			Boolean isSuccess = categoryServiceImpl.addCategory(name);
//			
//			responData.setData(isSuccess);
//			
//			return new ResponseEntity<>(responData, HttpStatus.OK);
//			
//		} catch (Exception e) {
//			Boolean isFalse = false;
//			responData.setData(isFalse);
//			
//	        return new ResponseEntity<>(responData, HttpStatus.BAD_REQUEST);
//		}
//		
//	}
//	
//	
//	@PutMapping("/update")
//	public ResponseEntity<?> updateCategory(@RequestParam int id, @RequestParam String name){
//		ResponData responData = new ResponData();
//		try {
//			
////			ResponData responData = new ResponData();
//			
//			Boolean isSuccess = categoryServiceImpl.updateCategory(id, name);
//			
//			responData.setData(isSuccess);
//			
//			return new ResponseEntity<>(responData, HttpStatus.OK);
//			
//		} catch (Exception e) {
//			Boolean isFalse = false;
//			responData.setData(isFalse);			
//	        return new ResponseEntity<>(responData, HttpStatus.BAD_REQUEST);
//		}
//		
//	}
//	
//	@DeleteMapping("/delete")
//	public ResponseEntity<?> delateCategory(@RequestParam int id){
//		ResponData responData = new ResponData();
//		try {
//			
////			ResponData responData = new ResponData();
//			
//			Boolean isSuccess = categoryServiceImpl.deleteCategory(id);
//			
//			responData.setData(isSuccess);
//			
//			return new ResponseEntity<>(responData, HttpStatus.OK);
//			
//		} catch (Exception e) {
//			Boolean isFalse = false;
//			responData.setData(isFalse);			
//	        return new ResponseEntity<>(responData, HttpStatus.BAD_REQUEST);
//		}
//		
//	}

}
