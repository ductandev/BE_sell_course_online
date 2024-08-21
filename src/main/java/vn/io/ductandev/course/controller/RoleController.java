package vn.io.ductandev.course.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.io.ductandev.course.dto.RoleDTO;
import vn.io.ductandev.course.entity.RoleEntity;
import vn.io.ductandev.course.request.RoleRequest;
import vn.io.ductandev.course.response.ResponseList;
import vn.io.ductandev.course.response.ResponseObject;
import vn.io.ductandev.course.service.RoleService;

@Tag(name = "Role")
@RestController()
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleService roleService;


    // ================================================
    //                  GET ALL ROLES
    // ================================================
    @GetMapping
    public ResponseEntity<?> getAllRoles() {
        Iterable<RoleDTO> roleDTO = roleService.getAllRoles();

        ResponseList<RoleDTO> response = new ResponseList<>(
                "Thành công !",
                HttpStatus.OK.value(),
                (List<RoleDTO>) roleDTO,
                new Date()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // ================================================
    //                  GET ROLE BY ID
    // ================================================
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject<RoleDTO>> getRoleById(@PathVariable int id) {
        Optional<RoleEntity> roleOptional = roleService.getRoleById(id);

        if(roleOptional.isPresent()) {
            RoleDTO roleDTO = convertToDTO(roleOptional.get());

            ResponseObject<RoleDTO> response = new ResponseObject<>(
                    "Thành công !",
                    HttpStatus.OK.value(),
                    roleDTO,
                    new Date()
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponseObject<RoleDTO> response = new ResponseObject<>(
                    "Không tìm thấy Role ID: " + id,
                    HttpStatus.NOT_FOUND.value(),
                    null,
                    new Date()
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    // ================================================
    //                  CREATE ROLE
    // ================================================
    @PostMapping
    public ResponseEntity<?> addRole(@RequestBody RoleRequest roleRequest) {

        roleService.addRole(roleRequest);


        ResponseObject<RoleRequest> response = new ResponseObject<>(
                "Thành công !",
                HttpStatus.CREATED.value(),
                roleRequest,
                new Date()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    // ================================================
    //                  DELETE ROLE
    // ================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        RoleDTO isUpdate = roleService.delete(id);

        ResponseObject<RoleDTO> response = new ResponseObject<>(
                "Thành công !",
                HttpStatus.OK.value(),
                isUpdate,
                new Date()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    // Helper method to convert RoleEntity to RoleDTO
    private RoleDTO convertToDTO(RoleEntity roleEntity) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(roleEntity.getId());
        roleDTO.setName(roleEntity.getName());
        roleDTO.setDescription(roleEntity.getDescription());
        return roleDTO;
    }
}
