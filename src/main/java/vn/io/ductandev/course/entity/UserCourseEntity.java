package vn.io.ductandev.course.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_user_course")
@IdClass(UserCourseId.class)
public class UserCourseEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private CourseEntity course;

    @Column(name = "date_buy")
    private java.sql.Date dateBuy;

    @Column(name = "is_delete", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int isDelete = 0;
}
