package vn.io.ductandev.course.controller;

import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.io.ductandev.course.dto.RoleDTO;
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
        List<RoleDTO> roleDTO = roleService.getAllRoles();

        ResponseList<RoleDTO> response = new ResponseList<>(
                "Thành công !",
                HttpStatus.OK.value(),
                (List<RoleDTO>) roleDTO,
                new Date()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
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

}
