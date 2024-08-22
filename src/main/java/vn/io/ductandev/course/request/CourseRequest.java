package vn.io.ductandev.course.request;

import java.util.Date;

public record CourseRequest(
		String title,
		Float price,
		String lecturer,
		String image,
		String description,
		int category_id
		) {
}
