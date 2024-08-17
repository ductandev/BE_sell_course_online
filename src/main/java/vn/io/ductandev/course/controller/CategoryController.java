package vn.io.ductandev.course.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.io.ductandev.course.dto.CategoryDTO;
import vn.io.ductandev.course.response.ResponseDTO;
import vn.io.ductandev.course.service.CategoryService;

@RestController
@RequestMapping("/api/v1/categorys")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping
    public ResponseEntity<?> getAllCategories() {
		
        List<CategoryDTO> categories = categoryService.getListCategory();
        ResponseDTO<CategoryDTO> response = new ResponseDTO<>(
                "Thành công !",
                HttpStatus.OK.value(),
                (List<CategoryDTO>) categories,
                new Date()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@PostMapping
	public ResponseEntity<?> addCategory(@RequestBody CategoryDTO categoryDTO){
		 Boolean isAdd = categoryService.addCategory(categoryDTO);

	        if (isAdd) {
	        	ResponseDTO<CategoryDTO> response = new ResponseDTO<>(
	                    "Thành công !",
	                    HttpStatus.OK.value(),
	                    (CategoryDTO) categoryDTO,
	                    new Date()
	            );
	            
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Failed to add category", HttpStatus.BAD_REQUEST);
	        }
		
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable int id, @RequestBody CategoryDTO categoryUpdateDTO) {
        Boolean isUpdate = categoryService.updateCategory(id, categoryUpdateDTO.getName());
        if (isUpdate) {
        	ResponseDTO<CategoryDTO> response = new ResponseDTO<>(
                    "Thành công !",
                    HttpStatus.OK.value(),
                    (CategoryDTO) categoryUpdateDTO,
                    new Date()
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
        	 ResponseDTO<String> response = new ResponseDTO<>(
                     "Failed to update category: Category not found with id " + id,
                     HttpStatus.NOT_FOUND.value(),
                     null,
                     new Date()
             );
             return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
	}

	
	@PatchMapping("/delete/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable int id) {
	    boolean isDelete = categoryService.deleteCategory(id);
	    
	    if (isDelete) {
        	ResponseDTO<CategoryDTO> response = new ResponseDTO<>(
                    "Thành công !",
                    HttpStatus.OK.value(),
                    (CategoryDTO) categoryService.getByID(id),
                    new Date()
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
        	 ResponseDTO<String> response = new ResponseDTO<>(
                     "Failed to delete category: Category not found with id " + id,
                     HttpStatus.NOT_FOUND.value(),
                     null,
                     new Date()
             );
             return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
	}


}
