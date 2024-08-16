package vn.io.ductandev.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.io.ductandev.course.model.CategoryDTO;
import vn.io.ductandev.course.payload.ResponData;
import vn.io.ductandev.course.service.CategoryService;

@RestController
@RequestMapping("/api/categorys")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getListCategory();
        return ResponseEntity.ok(categories);
    }
	
	@PostMapping
	public ResponseEntity<?> addCategory(@RequestBody CategoryDTO categoryDTO){
		ResponData responData = new ResponData();
	    try {
	        Boolean isSuccess = categoryService.addCategory(categoryDTO.getName());

	        responData.setData(isSuccess);

	        return new ResponseEntity<>(responData, HttpStatus.OK);
	    } catch (Exception e) {
	        Boolean isFalse = false;
	        responData.setData(isFalse);

	        return new ResponseEntity<>(responData, HttpStatus.BAD_REQUEST);
	    }
		
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<?> updateCategory(@RequestBody CategoryDTO categoryUpdateDTO) {
	    ResponData responData = new ResponData();
	    try {
	        Boolean isSuccess = categoryService.updateCategory(categoryUpdateDTO.getId(), categoryUpdateDTO.getName());
	        
	        responData.setData(isSuccess);
	        
	        return new ResponseEntity<>(responData, HttpStatus.OK);
	    } catch (Exception e) {
	        Boolean isFalse = false;
	        responData.setData(isFalse);
	        return new ResponseEntity<>(responData, HttpStatus.BAD_REQUEST);
	    }
	}

	
	@PatchMapping("/delete")
	public ResponseEntity<?> deleteCategory(@RequestBody CategoryDTO categoryDeleteDTO) {
	    boolean isUpdated = categoryService.deleteCategory(categoryDeleteDTO.getId());
	    
	    if (isUpdated) {
	        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
	    } else {        
	        return new ResponseEntity<>("Failed to delete category", HttpStatus.BAD_REQUEST);
	    }
	}


}
