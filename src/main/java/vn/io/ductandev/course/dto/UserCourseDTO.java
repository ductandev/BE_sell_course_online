package vn.io.ductandev.course.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class UserCourseDTO {

	private UserDTO user;

	private CourseDTO course;

	private Date dateBuy;

	private int isDelete;
}
