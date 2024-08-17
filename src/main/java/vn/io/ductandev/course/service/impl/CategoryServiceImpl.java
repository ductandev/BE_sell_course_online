package vn.io.ductandev.course.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.io.ductandev.course.entity.CategoryEntity;
import vn.io.ductandev.course.dto.CategoryDTO;
import vn.io.ductandev.course.repository.CategoryRepository;
import vn.io.ductandev.course.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<CategoryDTO> getListCategory() {
		
		List<CategoryEntity> liEntities = categoryRepository.findAll();
		
		List<CategoryDTO> listDTO = new ArrayList<>();
		
		for(CategoryEntity categoryEntity : liEntities) {
			
			CategoryDTO categoryDTO = new CategoryDTO();
			
			categoryDTO.setId(categoryEntity.getId());
			categoryDTO.setName(categoryEntity.getName());
			
			listDTO.add(categoryDTO);
			
		}
		return listDTO;
	}

	@Override
	public boolean addCategory(CategoryDTO categoryDTO) {
		boolean isSuccess = false;
		
		try {
			
			CategoryEntity categoryEntity = new CategoryEntity();
			
			categoryEntity.setName(categoryDTO.getName());
			categoryEntity.setId(categoryDTO.getId());
			
			categoryRepository.save(categoryEntity);
			
			isSuccess = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isSuccess;
	}

	@Override
	public boolean updateCategory(int id, String name) {
		boolean isSuccess = false;
		
		CategoryEntity categoryEntity = categoryRepository.getById(id);
		
		if(categoryEntity != null) {
			
			categoryEntity.setName(name);
			
			categoryRepository.save(categoryEntity);
			
			isSuccess = true;
		}
		
		
		return isSuccess;
	}

	@Override
	public boolean deleteCategory(int id) {
		
		boolean isSuccess = false;
		
		CategoryEntity categoryEntity = categoryRepository.getById(id);
		
		if(categoryEntity != null) {	
			
			categoryEntity.setIsDelete(1);
			
			categoryRepository.save(categoryEntity);
			
			isSuccess = true;
			return isSuccess;
		}
		
		return isSuccess;
	}

	@Override
	public CategoryDTO getByID(int id) {
		
		CategoryEntity c = categoryRepository.getById(id);
		
		CategoryDTO categoryDTO = new CategoryDTO();
		
		categoryDTO.setId(c.getId());
		categoryDTO.setIsDelete(c.getIsDelete());
		categoryDTO.setName(c.getName());
		
		return categoryDTO;
	}

}
