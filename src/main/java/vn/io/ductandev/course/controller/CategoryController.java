package vn.io.ductandev.course.controller;

import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.io.ductandev.course.dto.CategoryDTO;
import vn.io.ductandev.course.response.ResponseList;
import vn.io.ductandev.course.service.CategoryService;

@Tag(name = "Category")
@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping
    public ResponseEntity<?> getAllCategories() {
		
        List<CategoryDTO> categories = categoryService.getListCategory();
        ResponseList<CategoryDTO> response = new ResponseList<>(
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
	        	ResponseList<CategoryDTO> response = new ResponseList<>(
	                    "Thành công !",
	                    HttpStatus.OK.value(),
						(List<CategoryDTO>) categoryDTO,
	                    new Date()
	            );

	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Failed to add category", HttpStatus.BAD_REQUEST);
	        }

	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable int id, @RequestBody CategoryDTO categoryUpdateDTO) {
        Boolean isUpdate = categoryService.updateCategory(id, categoryUpdateDTO.getName());
        if (isUpdate) {
        	ResponseList<CategoryDTO> response = new ResponseList<>(
                    "Thành công !",
                    HttpStatus.OK.value(),
                    (List<CategoryDTO>) categoryUpdateDTO,
                    new Date()
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
        	 ResponseList<String> response = new ResponseList<>(
                     "Failed to update category: Category not found with id " + id,
                     HttpStatus.NOT_FOUND.value(),
                     null,
                     new Date()
             );
             return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable int id) {
	    boolean isDelete = categoryService.deleteCategory(id);
	    
	    if (isDelete) {
        	ResponseList<CategoryDTO> response = new ResponseList<>(
                    "Thành công !",
                    HttpStatus.OK.value(),
                    (List<CategoryDTO>) categoryService.getByID(id),
                    new Date()
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
        	 ResponseList<String> response = new ResponseList<>(
                     "Failed to delete category: Category not found with id " + id,
                     HttpStatus.NOT_FOUND.value(),
                     null,
                     new Date()
             );
             return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
	}


}
