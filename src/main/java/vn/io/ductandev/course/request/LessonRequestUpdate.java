package vn.io.ductandev.course.request;

public record LessonRequestUpdate( String name, String videoUrl
		, int isSuccess
		, int isDelete) {

}
