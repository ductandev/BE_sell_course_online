package vn.io.ductandev.course.model;

import lombok.Data;

@Data
public class PersonDTO {
	
	 	private int id;

	    private String username;

	    private String password;

	    private String firstName;

	    private String lastName;

	    private int isDelete = 0;

	    private RoleDTO role;
	
}
