package vn.io.ductandev.course.model;

import java.util.Date;

import lombok.Data;

@Data
public class CourseDTO {
	private int id;

    private String title;

    private Float price;

    private String lecturer;

    private Date createDate;

    private String image;

    private String description;

    private int isTopCourse = 0;

    private int isDelete = 0;

    private CategoryDTO category;
}
