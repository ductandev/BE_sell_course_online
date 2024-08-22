package vn.io.ductandev.course.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;

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

    private List<LessonDTO> lessonDTOs;;

    private CategoryDTO categoryDTO;

    private int isPublic;

    private int isDelete;
}
