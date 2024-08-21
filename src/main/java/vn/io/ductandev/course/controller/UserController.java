package vn.io.ductandev.course.controller;

import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import vn.io.ductandev.course.dto.UserDTO;
import vn.io.ductandev.course.entity.UserEntity;
import vn.io.ductandev.course.request.UserRequest;
import vn.io.ductandev.course.request.UserRequestPatch;
import vn.io.ductandev.course.response.ResponseList;

import vn.io.ductandev.course.response.ResponseObject;
import vn.io.ductandev.course.service.UserService;

@Tag(name = "User")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

	// ================================================
	//               	GET ALL USER
	// ================================================
    @GetMapping
    public ResponseEntity<?> getAllUser() {
        List<UserDTO> userDTOS = userService.getListUser();

        ResponseList<UserDTO> response = new ResponseList<>(
                "Thành công !",
                HttpStatus.OK.value(),
                (List<UserDTO>) userDTOS,
                new Date()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	// ================================================
	//               	CREATE USER
	// ================================================
    @PostMapping()
    public ResponseEntity<?> addUser(@RequestBody UserRequest userRequest) {

        Boolean isAdd = userService.addUser(userRequest);

        if (isAdd) {
        	ResponseObject<UserRequest> response = new ResponseObject<>(
                    "Thêm thành công !",
                    HttpStatus.OK.value(),
                    userRequest,
                    new Date()
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to add user", HttpStatus.BAD_REQUEST);
        }
    }

	// ================================================
	//               	UPDATE USER
	// ================================================
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserRequestPatch userRequestPatch) {
        UserEntity isUpdated = userService.updateUser(id, userRequestPatch);

        if (isUpdated != null) {
        	ResponseObject<UserRequestPatch> response = new ResponseObject<>(
                    "Update thành công !",
                    HttpStatus.OK.value(),
                    userRequestPatch,
                    new Date()
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
        	 ResponseList<String> response = new ResponseList<>(
                     "Failed to update user: User not found with id " + id,
                     HttpStatus.NOT_FOUND.value(),
                     null,
                     new Date()
             );
             return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

	// ================================================
	//               	DELETE USER
	// ================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        boolean isDelete = userService.deleteUser(id);
        if (isDelete) {
        	ResponseList<UserDTO> response = new ResponseList<>(
                    "Delete thành công !",
                    HttpStatus.OK.value(),
                    (List<UserDTO>) userService.getbyID(id),
                    new Date()
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
        	 ResponseList<String> response = new ResponseList<>(
                     "Failed to delete user: User not found with id " + id,
                     HttpStatus.NOT_FOUND.value(),
                     null,
                     new Date()
             );
             return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
	
//	  private UserDTO convertToDTO(userEntity userEntity) {
//		    UserDTO personDTO = new UserDTO();
//		    personDTO.setId(userEntity.getId());
//		    personDTO.setUsername(userEntity.getUsername());
//		    personDTO.setPassword(userEntity.getPassword());
//		    personDTO.setFirstName(userEntity.getFirstName());
//		    personDTO.setLastName(userEntity.getLastName());
//		    personDTO.setIsDelete(userEntity.getIsDelete());
//		    
//		    RoleEntity role = userEntity.getRole();
//		    
//		    RoleDTO roleDTO = new RoleDTO();
//		    roleDTO.setId(role.getId());
//		    roleDTO.setName(role.getName());
//		    
//		    personDTO.setRole(roleDTO);
//		    
//		    return personDTO;
//		}
//
//	  private userEntity convertToEntity(UserDTO personDTO) {
//		    userEntity userEntity = new userEntity();
//		    userEntity.setId(personDTO.getId());
//		    userEntity.setUsername(personDTO.getUsername());
//		    userEntity.setPassword(personDTO.getPassword());
//		    userEntity.setFirstName(personDTO.getFirstName());
//		    userEntity.setLastName(personDTO.getLastName());
//		    userEntity.setIsDelete(personDTO.getIsDelete());
//		    
//		    RoleDTO roleDTO = personDTO.getRole();
//		    
//		    RoleEntity roleEntity = new RoleEntity();
//		    roleEntity.setId(roleDTO.getId());
//		    roleEntity.setName(roleDTO.getName());
//		    
//		    userEntity.setRole(roleEntity);
//		    
//		    return userEntity;
//		}

}
