package vn.io.ductandev.course.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import vn.io.ductandev.course.dto.CategoryDTO;
import vn.io.ductandev.course.request.CategoryRequest;
import vn.io.ductandev.course.response.ResponseList;
import vn.io.ductandev.course.response.ResponseObject;
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
	public ResponseEntity<?> addCategory(@RequestBody CategoryRequest categoryRequest){
			categoryService.addCategory(categoryRequest);

	       
			ResponseObject<CategoryRequest> response = new ResponseObject<>(
	                    "Thành công !",
	                    HttpStatus.OK.value(),
	                    categoryRequest,
	                    new Date()
	            );

	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } 

	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable int id, @RequestBody CategoryRequest categoryRequest) {
        CategoryRequest isUpdate = categoryService.updateCategory(id, categoryRequest);
        if (isUpdate != null) {
        	ResponseObject<CategoryRequest> response = new ResponseObject<>(
                    "Thành công !",
                    HttpStatus.OK.value(),
                    categoryRequest,
                    new Date()
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
        	ResponseObject<CategoryRequest> response = new ResponseObject<>(
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
		CategoryDTO categoryDTO = categoryService.deleteCategory(id);
	    
	    if (categoryDTO != null) {
        	ResponseObject<CategoryDTO> response = new ResponseObject<>(
                    "Thành công !",
                    HttpStatus.OK.value(),
                    categoryDTO,
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


	@GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        try {
            CategoryDTO categoryDTO = categoryService.getByID(id);
            if (categoryDTO != null) {
                ResponseObject<CategoryDTO> response = new ResponseObject<>(
                        "Get Category successfully!",
                        HttpStatus.OK.value(),
                        categoryDTO,
                        new Date()
                );
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ResponseObject<String> response = new ResponseObject<>(
                        "Failed to get category: Category not found with id " + id,
                        HttpStatus.NOT_FOUND.value(),
                        null,
                        new Date()
                );
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ResponseObject<String> response = new ResponseObject<>(
                    "An error occurred while retrieving the user: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    null,
                    new Date()
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	
	 @GetMapping("/pagination")
	    public ResponseEntity<?> getCategories(
	            @RequestParam(required = false) String nameCourse,
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int limit,
	            @RequestParam( required = false) int categoryID) {

	        Page<CategoryDTO> categories = categoryService.getCategories(nameCourse, page, limit, categoryID);

	        return new ResponseEntity<>(categories, HttpStatus.OK);
	    }
	
}
