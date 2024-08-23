package vn.io.ductandev.course.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.io.ductandev.course.dto.RoleDTO;
import vn.io.ductandev.course.dto.UserDTO;
import vn.io.ductandev.course.request.AuthenRequest;
import vn.io.ductandev.course.request.UserRequest;
import vn.io.ductandev.course.response.ResponseObject;
import vn.io.ductandev.course.service.AuthenService;

import java.util.Date;

@Tag(name = "Auth")
@RestController
@RequestMapping("api/auth")
public class AuthenController {

    @Autowired
    private AuthenService authenService;

    // ================================================
    //               	    SIGN IN
    // ================================================
    @PostMapping("/sign-in")
    public ResponseEntity<?> authen(@RequestBody AuthenRequest authenRequest){
        try {
            UserDTO userDTO = authenService.checkLogin(authenRequest);

            if(userDTO != null) {
                ResponseObject<UserDTO> response = new ResponseObject<>(
                        "Login Success !!!",
                        HttpStatus.OK.value(),
                        userDTO,
                        new Date()
                );

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ResponseObject<RoleDTO> response = new ResponseObject<>(
                        "Login fail !!!",
                        HttpStatus.NOT_FOUND.value(),
                        null,
                        new Date()
                );

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e){
            ResponseObject<String> response = new ResponseObject<>(
                    "An error occurred while retrieving the user: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    null,
                    new Date()
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // ================================================
    //               	    SIGN UP
    // ================================================
    @PostMapping("/sign-up")
    public ResponseEntity<?> addUser(@RequestBody UserRequest userRequest) {

        Boolean isAdd = authenService.addUser(userRequest);

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

}
