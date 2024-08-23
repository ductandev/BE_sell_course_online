package vn.io.ductandev.course.service;

import vn.io.ductandev.course.dto.UserDTO;
import vn.io.ductandev.course.entity.UserEntity;
import vn.io.ductandev.course.request.AuthenRequest;
import vn.io.ductandev.course.request.UserRequest;

public interface AuthenService {
    UserDTO checkLogin(AuthenRequest authenRequest);
    boolean addUser(UserRequest userRequest);
}
