package vn.io.ductandev.course.request;

public record LessonRequestPatch(
        String name,
        String videoUrl,
        int isSuccess,
        Integer courseID,
        int isDelete
) {
}
