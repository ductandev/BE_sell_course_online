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
@Table(name = "tb_comment")
@Data
public class CommentEntity {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @Column(name = "description")
	    private String description;

	    @Column(name = "rate")
	    private int rate;

	    @Column(name = "date_comment")
	    @Temporal(TemporalType.DATE)
	    private Date dateComment;

	    @Column(name = "is_Delete", nullable = false, columnDefinition = "INT DEFAULT 0")
	    private int isDelete = 0;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "person_id", referencedColumnName = "id")
	    private PersonEntity person;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "course_id", referencedColumnName = "id")
	    private CourseEntity course;

}
