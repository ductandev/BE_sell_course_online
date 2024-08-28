package vn.io.ductandev.course.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import vn.io.ductandev.course.dto.CategoryDTO;
import vn.io.ductandev.course.entity.CategoryEntity;
import vn.io.ductandev.course.exception.CategoryNotFoundException;
import vn.io.ductandev.course.repository.CategoryRepository;
import vn.io.ductandev.course.request.CategoryRequest;
import vn.io.ductandev.course.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<CategoryDTO> getListCategory() {
		
		List<CategoryEntity> listEntities = categoryRepository.findAll();
		
		List<CategoryDTO> listDTO = new ArrayList<>();
		
		for(CategoryEntity categoryEntity : listEntities) {
			
			CategoryDTO categoryDTO = new CategoryDTO();
			
			categoryDTO.setId(categoryEntity.getId());
			categoryDTO.setName(categoryEntity.getName());
			
			listDTO.add(categoryDTO);
			
		}
		return listDTO;
	}

	@Override
	public boolean addCategory(CategoryRequest categoryRequest) {
		boolean isSuccess = false;
		
		try {
			
			CategoryEntity categoryEntity = new CategoryEntity();
			
			categoryEntity.setName(categoryRequest.name());
//			categoryEntity.setId(categoryDTO.getId());
			
			categoryRepository.save(categoryEntity);
			
			isSuccess = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isSuccess;
	}

	@Override
	public CategoryRequest updateCategory(int id, CategoryRequest categoryRequest) {
		
		CategoryEntity categoryEntity = categoryRepository.getById(id);
		
		if(categoryEntity != null) {
			
			try {
				categoryEntity.setName(categoryRequest.name());
				categoryRepository.save(categoryEntity);
				return categoryRequest;
			} catch (Exception e) {
				System.out.println("Lỗi Update !");
			}
		}
			return null;
	}

	@Override
	public CategoryDTO deleteCategory(int id) {
		
		try {
			
			CategoryEntity categoryEntity = categoryRepository.getById(id);
			categoryEntity.setIsDelete(1);
			CategoryDTO categoryDTO = new CategoryDTO();
			
			if(categoryEntity != null) {
				
				categoryEntity.setIsDelete(1);
				
				
				
				categoryDTO.setId(categoryEntity.getId());
				categoryDTO.setName(categoryEntity.getName());
				categoryEntity.setIsDelete(categoryEntity.getIsDelete());
				
				categoryRepository.save(categoryEntity);
				
			}
			
			return categoryDTO;
			
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public CategoryDTO getByID(int id) {
		
		
		CategoryEntity c = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
		
		if(c != null) {
			CategoryDTO categoryDTO = new CategoryDTO();
			
			categoryDTO.setId(c.getId());
			categoryDTO.setName(c.getName());
			categoryDTO.setIsDelete(c.getIsDelete());
			
			return categoryDTO;
		}
		return null;
	}

	@Override
	public Page<CategoryDTO> getCategories(String nameCourse, int page, int limit, int categoryID) {

	        
//	        Pageable pageable = PageRequest.of(page, limit);
//	        Page<CategoryEntity> categoryEntities = categoryRepository.findCategoriesWithFilters(nameCourse , pageable);
//
//	        return categoryEntities.map(this::convertToDTO);
		
		 Pageable pageable = PageRequest.of(page, limit);

	        Page<CategoryEntity> categoryEntities = categoryRepository.findByNameCourseAndCategoryID(nameCourse, categoryID, pageable);

	        return categoryEntities.map(this::convertToDTO);
	}
	
	
	 private CategoryDTO convertToDTO(CategoryEntity categoryEntity) {
	        CategoryDTO dto = new CategoryDTO();
	        dto.setId(categoryEntity.getId());
	        dto.setName(categoryEntity.getName());
	        dto.setIsDelete(categoryEntity.getIsDelete());
	        return dto;
	    }

}
