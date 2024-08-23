package vn.io.ductandev.course.dto;

import lombok.Data;

@Data
public class UserDTO {

	private int id;

	private String username;

	private String email;

	private String password;

	private String avatar;

	private int isDelete;

	private int role;

	// Không bao gồm mật khẩu trong DTO để bảo mật
	// Nếu cần truyền mật khẩu, bạn có thể tạo một DTO khác hoặc thêm vào nếu cần thiết
}
