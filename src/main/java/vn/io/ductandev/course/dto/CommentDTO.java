package vn.io.ductandev.course.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class CommentDTO {

    private int id;

    private String description;

    private int rate;

    private Date dateComment;

    private UserDTO user;

    private CourseDTO course;

    private int isDelete;
}
