package vn.io.ductandev.course.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.io.ductandev.course.dto.UserDTO;
import vn.io.ductandev.course.entity.RoleEntity;
import vn.io.ductandev.course.entity.UserEntity;
import vn.io.ductandev.course.repository.RoleRepository;
import vn.io.ductandev.course.repository.UserRepository;
import vn.io.ductandev.course.request.AuthenRequest;
import vn.io.ductandev.course.request.UserRequest;
import vn.io.ductandev.course.service.AuthenService;

@Service
public class AuthenServiceImpl implements AuthenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO checkLogin(AuthenRequest request) {
        UserEntity user = userRepository.findUserEntityByEmail(request.email());

        if(user != null && passwordEncoder.matches(request.password(), user.getPassword())){
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword(user.getPassword());
            userDTO.setAvatar(user.getAvatar());
            userDTO.setRole(user.getRole().getId());

            return userDTO;
        }
        return null;
    }


    // ================================================
    //               	CREATE USER
    // ================================================
    @Override
    public boolean addUser(UserRequest userRequest) {

        boolean isSuccess = false;

        try {
            RoleEntity roleEntity = roleRepository.getById(2);

            UserEntity userEntity = new UserEntity();

            userEntity.setUsername(userRequest.username());

            // Mã hóa mật khẩu bằng BCrypt trước khi lưu
            String encodedPassword = passwordEncoder.encode(userRequest.password());
            userEntity.setPassword(encodedPassword);

            userEntity.setEmail(userRequest.email());
            userEntity.setAvatar(userRequest.avatar());

            userEntity.setRole(roleEntity);

            userRepository.save(userEntity);

            isSuccess = true;
            return isSuccess;
        } catch (Exception e) {
            return isSuccess;
        }
    }
}
