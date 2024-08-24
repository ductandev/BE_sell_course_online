package vn.io.ductandev.course.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.io.ductandev.course.entity.CategoryEntity;
import vn.io.ductandev.course.dto.CategoryDTO;
import vn.io.ductandev.course.repository.CategoryRepository;
import vn.io.ductandev.course.request.CategoryRequest;
import vn.io.ductandev.course.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;


	// ================================================
	//                GET ALL CATEGORY
	// ================================================
	@Override
	public List<CategoryDTO> getListCategory() {
		List<CategoryEntity> listEntities = categoryRepository.findAllByIsDeleteFalse();

		List<CategoryDTO> listDTO = new ArrayList<>();

		for(CategoryEntity categoryEntity : listEntities) {
			CategoryDTO categoryDTO = new CategoryDTO();

			categoryDTO.setId(categoryEntity.getId());
			categoryDTO.setName(categoryEntity.getName());
			categoryDTO.setIsDelete(categoryEntity.getIsDelete());

			listDTO.add(categoryDTO);
		}
		return listDTO;
	}

	// ================================================
	//                CREATE CATEGORY
	// ================================================
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

	// ================================================
	//             UPDATE CATEGORY BY ID
	// ================================================
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

	// ================================================
	//             DELETE CATEGORY BY ID
	// ================================================
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


	// ================================================
	//                GET CATEGORY BY ID
	// ================================================
	@Override
	public CategoryDTO getByID(int id) {
		try{
			CategoryEntity categoryEntity = categoryRepository.getById(id);

			CategoryDTO categoryDTO = new CategoryDTO();

			categoryDTO.setId(categoryEntity.getId());
			categoryDTO.setIsDelete(categoryEntity.getIsDelete());
			categoryDTO.setName(categoryEntity.getName());

			return categoryDTO;

		} catch (Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
}
