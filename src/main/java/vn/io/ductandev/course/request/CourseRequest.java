package vn.io.ductandev.course.request;

import java.util.Date;

public record CourseRequest(int id,
		String title,
		Float price,
		String lecturer,
		String image,
		String description,
		Date createDate
		) {

}
