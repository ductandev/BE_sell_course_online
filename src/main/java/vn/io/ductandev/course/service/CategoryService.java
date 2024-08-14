package vn.io.ductandev.course.service;

import java.util.List;

import vn.io.ductandev.course.model.CategoryDTO;

public interface CategoryService  {
	
	List<CategoryDTO> getListCategory();
	
	boolean addCategory(String name);
	
	boolean updateCategory(int id, String name);
	
	boolean deleteCategory(int id);
	

}
