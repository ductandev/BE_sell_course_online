package vn.io.ductandev.course.request;

public record CourseRequestUpdate(
		String title,
		Float price,
		String lecturer,
		String image,
		String description,
		int isTopCourse,
		int isFree,
		int isPublic,
		int isDelete
		) {
}
