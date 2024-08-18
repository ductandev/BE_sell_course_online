package vn.io.ductandev.course.service;

import vn.io.ductandev.course.entity.PersonEntity;
import vn.io.ductandev.course.request.AuthenRequest;

public interface AuthenService {
    boolean checkLogin(AuthenRequest authenRequest);
}
