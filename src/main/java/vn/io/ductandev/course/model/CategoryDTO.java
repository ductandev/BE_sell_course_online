package vn.io.ductandev.course.model;

import java.util.List;

import lombok.Data;

@Data
public class CategoryDTO {

	 	private int id;

	    private String name;

	    private int isDelete = 0;

	    private List<CourseDTO> courses;
	
}
