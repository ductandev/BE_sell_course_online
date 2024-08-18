package vn.io.ductandev.course.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.io.ductandev.course.dto.PersonDTO;
import vn.io.ductandev.course.entity.PersonEntity;
import vn.io.ductandev.course.request.AuthenRequest;
import vn.io.ductandev.course.response.ResponseDTO;
import vn.io.ductandev.course.service.AuthenService;

import java.util.Date;

@Tag(name = "Auth")
@RestController
@RequestMapping("/auth")
public class AuthenController {

    @Autowired
    private AuthenService authenService;

    @PostMapping("/sign-in")
    public ResponseEntity<?> authen(@RequestBody AuthenRequest authenRequest){
        boolean isSuccess = authenService.checkLogin(authenRequest);

        ResponseDTO<PersonDTO> response = new ResponseDTO<>(
                "Login Success !",
                HttpStatus.OK.value(),
                isSuccess,
                new Date()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
