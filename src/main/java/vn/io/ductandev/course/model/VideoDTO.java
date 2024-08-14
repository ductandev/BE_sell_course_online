package vn.io.ductandev.course.model;

import lombok.Data;

@Data
public class VideoDTO {

    private int id;

    private String name;

    private String videoUrl;

    private int isDelete = 0;

    private CourseDTO course;
	
}
