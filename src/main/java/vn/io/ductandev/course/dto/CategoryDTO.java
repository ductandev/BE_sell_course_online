package vn.io.ductandev.course.dto;

import lombok.Data;

@Data
public class CategoryDTO {

	 	private int id;

	    private String name;

	    private int isDelete = 0;

//	    private List<CourseDTO> courses;
	
}
