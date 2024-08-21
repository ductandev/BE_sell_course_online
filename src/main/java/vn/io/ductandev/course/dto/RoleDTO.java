package vn.io.ductandev.course.dto;

import lombok.Data;

@Data
public class RoleDTO {

	private int id;

	private String name;

	private String description;

	private int isDelete;
}
