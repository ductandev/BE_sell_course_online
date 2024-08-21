package vn.io.ductandev.course.dto;

import lombok.Data;

@Data
public class LessonByIdDTO {

    private int id;

    private String name;

    private String videoUrl;

    private int isSuccess;

    private CourseDTO course;

    private int isDelete;
}
