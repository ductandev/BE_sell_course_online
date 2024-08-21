package vn.io.ductandev.course.service;

import java.util.List;

import vn.io.ductandev.course.dto.CategoryDTO;
import vn.io.ductandev.course.request.CategoryRequest;

public interface CategoryService  {
	
	List<CategoryDTO> getListCategory();
	
	boolean addCategory(CategoryRequest categoryRequest);
	
	CategoryRequest updateCategory(int id, CategoryRequest categoryRequest);
	
	CategoryDTO deleteCategory(int id);
	
	CategoryDTO getByID(int id);
	

}
