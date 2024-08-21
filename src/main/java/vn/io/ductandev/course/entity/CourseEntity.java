package vn.io.ductandev.course.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "tb_course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price")
    private Float price;

    @Column(name = "lecturer")
    private String lecturer;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "create_date")
    private java.sql.Date createDate;

    @Column(name = "is_top_course", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int isTopCourse = 0;

    @Column(name = "is_free", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int isFree = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CategoryEntity category;

    @Column(name = "is_public", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int isPublic = 0;

    @Column(name = "is_delete", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int isDelete = 0;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LessonEntity> lessons;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserCourseEntity> userCourses;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentEntity> comments;
}
