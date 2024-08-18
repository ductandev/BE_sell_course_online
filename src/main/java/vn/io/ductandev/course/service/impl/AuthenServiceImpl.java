package vn.io.ductandev.course.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.io.ductandev.course.entity.PersonEntity;
import vn.io.ductandev.course.repository.PersonRepository;
import vn.io.ductandev.course.request.AuthenRequest;
import vn.io.ductandev.course.service.AuthenService;

@Service
public class AuthenServiceImpl implements AuthenService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean checkLogin(AuthenRequest request) {
        boolean isSuccess = false;
        PersonEntity user = personRepository.findPersonEntityByUsername(request.email());
        if(user != null && passwordEncoder.matches(request.password(), user.getPassword())){
            isSuccess = true;
        }

        return isSuccess;
    }
}
