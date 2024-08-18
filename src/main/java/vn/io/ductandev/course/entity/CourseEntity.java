package vn.io.ductandev.course.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "TB_COURSE" ,schema = "COURSE") 
@Data
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

    @Column(name = "create_Date")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "is_TopCourse", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int isTopCourse = 0;

    @Column(name = "is_Delete", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int isDelete = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cate_id", referencedColumnName = "id")
    private CategoryEntity category;
	
}
