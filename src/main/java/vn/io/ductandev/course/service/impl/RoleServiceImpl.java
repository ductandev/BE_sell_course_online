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
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    // ================================================
    //                  GET ALL ROLES
    // ================================================
    @Override
    public List<RoleDTO> getAllRoles() {
        List<RoleEntity> listEntity = roleRepository.findAllByIsDeleteFalse();

        List<RoleDTO> listDTO = new ArrayList<>();

        for (RoleEntity r : listEntity){
            RoleDTO roleDTO = new RoleDTO();

            roleDTO.setDescription(r.getDescription());
            roleDTO.setName(r.getName());
            roleDTO.setId(r.getId());

            listDTO.add(roleDTO);
        }


        return listDTO;
    }

    // ================================================
    //                GET ROLES BY ID
    // ================================================
    @Override
    public Optional<RoleEntity> getRoleById(int id) {
        return roleRepository.findByIdAndIsDeleteFalse(id);
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
}
