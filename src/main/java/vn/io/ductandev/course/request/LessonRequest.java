package vn.io.ductandev.course.request;

import vn.io.ductandev.course.dto.CourseDTO;

public record LessonRequest( String name, String videoUrl, int courseID) {

}
