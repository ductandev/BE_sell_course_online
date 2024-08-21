package vn.io.ductandev.course.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_lession")
public class LessonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "video_url", nullable = false)
    private String videoUrl;

    @Column(name = "is_success", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int isSuccess = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private CourseEntity course;

    @Column(name = "is_delete", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int isDelete = 0;
}
