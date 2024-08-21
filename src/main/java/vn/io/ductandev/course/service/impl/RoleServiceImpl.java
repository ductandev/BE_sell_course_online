package vn.io.ductandev.course.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.io.ductandev.course.dto.RoleDTO;
import vn.io.ductandev.course.entity.RoleEntity;
import vn.io.ductandev.course.repository.RoleRepository;
import vn.io.ductandev.course.request.RoleRequest;
import vn.io.ductandev.course.service.RoleService;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    // ================================================
    //                  GET ALL ROLES
    // ================================================
    @Override
    public List<RoleDTO> getAllRoles() {
        // Tạo ra 1 list userDTOS rỗng
        List<RoleDTO> roleDTOS = new ArrayList<>();

        // LẤy danh sách user mà mình truy vấn được ở database thônng qua hàm findall
        List<RoleEntity> roleEntities = roleRepository.findAll();

        // Custom dữ liệu trả về bằng DTO
        for (RoleEntity data : roleEntities) {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(data.getId());
            roleDTO.setName(data.getName());
            roleDTO.setDescription(data.getDescription());
            roleDTO.setIsDelete(data.getIsDelete());

            // Add vào List<>
            roleDTOS.add(roleDTO);
        }

        return roleDTOS;
    }

    // ================================================
    //                  CREATE ROLE
    // ================================================
    @Override
    public Boolean addRole(RoleRequest roleRequest) {
        try {
            RoleEntity roleEntity = new RoleEntity();

            roleEntity.setName(roleRequest.name());
            roleEntity.setDescription(roleRequest.description());

            roleRepository.save(roleEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ================================================
    //                  DELETE ROLE
    // ================================================
    @Override
    public RoleDTO delete(int id) {

        try {
            RoleEntity roleEntity = roleRepository.getById(id);
            roleEntity.setIsDelete(1);
            roleRepository.save(roleEntity);
            RoleDTO roleDTO = new RoleDTO();

            if (roleEntity != null) {
                roleDTO.setId(roleEntity.getId());
                roleDTO.setName(roleEntity.getName());
                roleDTO.setDescription(roleEntity.getDescription());
                roleDTO.setIsDelete(roleEntity.getIsDelete());
            }
            return roleDTO;

        } catch (Exception e) {
            return null;
        }

    }

    private RoleDTO convertToDTO(RoleEntity roleEntity) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(roleEntity.getId());
        roleDTO.setName(roleEntity.getName());
        return roleDTO;
    }

    private RoleEntity convertToEntity(RoleDTO roleDTO) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(roleDTO.getName());
        roleEntity.setDescription(roleDTO.getDescription());
        return roleEntity;
    }


//	@Override
//	public boolean addRole(String name) {
//		boolean isSuccess = false;
//		
//		try {
//			
//			RoleEntity roleEntity = new RoleEntity();
//			
//			roleEntity.setName(name);
//			
//			roleRepository.save(roleEntity);
//			
//			isSuccess = true;
//			
//		} catch (Exception e) {
//			System.out.println("Error add role : " + e);
//		}
//		
//		return isSuccess;
//	}


}
