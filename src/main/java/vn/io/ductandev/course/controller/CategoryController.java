package vn.io.ductandev.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.io.ductandev.course.dto.CategoryDTO;
import vn.io.ductandev.course.payload.ResponData;
import vn.io.ductandev.course.service.CategoryService;

@RestController
@RequestMapping("/api/v1/categorys")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getListCategory();
        return ResponseEntity.ok(categories);
    }
	
	@PostMapping("/add")
	public ResponseEntity<?> addCategory(@RequestParam String name){
		ResponData responData = new ResponData();
		try {
			
//			ResponData responData = new ResponData();
			
			Boolean isSuccess = categoryService.addCategory(name);
			
			responData.setData(isSuccess);
			
			return new ResponseEntity<>(responData, HttpStatus.OK);
			
		} catch (Exception e) {
			Boolean isFalse = false;
			responData.setData(isFalse);
			
	        return new ResponseEntity<>(responData, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<?> updateCategory(@RequestParam int id, @RequestParam String name){
		ResponData responData = new ResponData();
		try {
			
//			ResponData responData = new ResponData();
			
			Boolean isSuccess = categoryService.updateCategory(id, name);
			
			responData.setData(isSuccess);
			
			return new ResponseEntity<>(responData, HttpStatus.OK);
			
		} catch (Exception e) {
			Boolean isFalse = false;
			responData.setData(isFalse);			
	        return new ResponseEntity<>(responData, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteCategory(@RequestParam int id){
	
			boolean isUpdated = categoryService.deleteCategory(id);
	        
	        if (isUpdated) {
	            return new ResponseEntity<>("Category delete successfully", HttpStatus.OK);
	        } else {		
	            return new ResponseEntity<>("Failed to delete category", HttpStatus.BAD_REQUEST);
	        }
		
	}

}
