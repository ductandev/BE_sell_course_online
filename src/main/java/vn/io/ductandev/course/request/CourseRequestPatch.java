package vn.io.ductandev.course.request;

import vn.io.ductandev.course.dto.CategoryDTO;
import vn.io.ductandev.course.dto.LessonDTO;

import java.util.Date;
import java.util.List;

public record CourseRequestPatch(

        String title,

        Float price,

        String lecturer,

        String image,

        String description,

        Date createDate,

        int isTopCourse,

        int isFree,

        int categoryId,

        int isPublic,

        int isDelete

) {
}
