package vn.io.ductandev.course.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserByIdDTO {
	 private int id;
	    private String username;
	    private String email;
	    private String avatar;
	    private List<CourseDTO> courses;
}
