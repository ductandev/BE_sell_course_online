package vn.io.ductandev.course.dto;

import lombok.Data;
import vn.io.ductandev.course.entity.RoleEntity;

@Data
public class UserDTO {

	private int id;

	private String username;

	private String email;

	private String password;

	private String avatar;

	private int isDelete;

	private int role;

}
