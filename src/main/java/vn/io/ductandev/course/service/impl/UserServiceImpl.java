package vn.io.ductandev.course.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.io.ductandev.course.config.Config;
import vn.io.ductandev.course.config.Mapper;
import vn.io.ductandev.course.entity.UserEntity;
import vn.io.ductandev.course.entity.CourseEntity;
import vn.io.ductandev.course.entity.RoleEntity;
import vn.io.ductandev.course.dto.UserDTO;
import vn.io.ductandev.course.dto.CourseDTO;
import vn.io.ductandev.course.dto.RoleDTO;
import vn.io.ductandev.course.dto.UserByIdDTO;
import vn.io.ductandev.course.repository.UserRepository;
import vn.io.ductandev.course.repository.RoleRepository;
import vn.io.ductandev.course.repository.UserCourseRepository;
import vn.io.ductandev.course.request.UserRequest;
import vn.io.ductandev.course.request.UserRequestPatch;
import vn.io.ductandev.course.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    UserCourseRepository userCourseRepository;

    @Autowired
    Config appConfig;

    @Autowired
    Mapper<UserDTO> personMapper;

    // ================================================
    //               	GET ALL USER
    // ================================================
    @Override
    public List<UserDTO> getListUser() {
        List<UserEntity> listEntities = userRepository.findAll();

        List<UserDTO> listDtos = new ArrayList<>();

        for (UserEntity p : listEntities) {

            UserDTO userDTO = new UserDTO();
            userDTO.setId(p.getId());
            userDTO.setUsername(p.getUsername());
            userDTO.setPassword(p.getPassword());
            userDTO.setEmail(p.getEmail());
            userDTO.setAvatar(p.getAvatar());

            RoleEntity role = roleRepository.getById(p.getRole().getId());

            RoleDTO roleDTO = new RoleDTO();

            roleDTO.setId(role.getId());
            roleDTO.setName(role.getName());

            userDTO.setRole(p.getRole().getId());

            listDtos.add(userDTO);
        }

        return listDtos;
    }

    // ================================================
    //               	GET USER BY ID
    // ================================================
    @Override
    public UserByIdDTO getbyID(int id) {
        UserEntity userEntity  = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CourseDTO> courses = userCourseRepository.findByUserId(id).stream()
                .map(uc -> convertToCourseDTO(uc.getCourse()))
                .collect(Collectors.toList());

        UserByIdDTO userDTO = convertToUserDTO(userEntity);
        userDTO.setCourses(courses);
        return userDTO;
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
            userEntity.setPassword(userRequest.password());
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

    // ================================================
    //               	UPDATE USER
    // ================================================
    @Override
    public UserEntity updateUser(int id, UserRequestPatch userRequestPatch) {
        try {
            UserEntity userEntity = userRepository.getById(id);
            if (userEntity != null && userEntity.getIsDelete() == 0) {
                userEntity.setAvatar(userRequestPatch.avatar());
                userEntity.setPassword(userRequestPatch.password());
                userEntity.setUsername(userRequestPatch.username());

                userRepository.save(userEntity);
            }
            return userEntity;

        } catch (Exception e) {
            return null;
        }

    }

    // ================================================
    //               	DELETE USER
    // ================================================
    @Override
    public boolean deleteUser(int id) {

        Boolean isSuccess = false;

        try {
            UserEntity user = userRepository.getById(id);

            user.setIsDelete(1);

            userRepository.save(user);

            isSuccess = true;

            return isSuccess;
        } catch (Exception e) {
            return isSuccess;
        }
    }


    
    private UserByIdDTO convertToUserDTO(UserEntity userEntity) {
        UserByIdDTO dto = new UserByIdDTO();
        dto.setId(userEntity.getId());
        dto.setUsername(userEntity.getUsername());
        dto.setEmail(userEntity.getEmail());
        dto.setAvatar(userEntity.getAvatar());
        dto.setRole_id(userEntity.getRole().getId());
        return dto;
    }

    private CourseDTO convertToCourseDTO(CourseEntity courseEntity) {
        CourseDTO dto = new CourseDTO();
        dto.setId(courseEntity.getId());
        dto.setTitle(courseEntity.getTitle());
        dto.setPrice(courseEntity.getPrice());
        dto.setLecturer(courseEntity.getLecturer());
        dto.setImage(courseEntity.getImage());
        dto.setDescription(courseEntity.getDescription());
        return dto;
    }
}