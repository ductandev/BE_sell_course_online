package vn.io.ductandev.course.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class CourseDTO {

    private int id;

    private String title;

    private Float price;

    private String lecturer;

    private String image;

    private String description;

    private Date createDate;

    private int isTopCourse;

    private int isFree;

    private CategoryDTO category;

    private int isPublic;

    private int isDelete;
}
