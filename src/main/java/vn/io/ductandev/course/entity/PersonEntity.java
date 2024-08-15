package vn.io.ductandev.course.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="tb_person")
@Data
public class PersonEntity {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @Column(name = "username", unique = true, nullable = false)
	    private String username;

	    @Column(name = "password", nullable = false)
	    private String password;

	    @Column(name="FIRSTNAME")
	    private String firstName;

	    @Column(name="LASTNAME")
	    private String lastName;

	    @Column(name = "is_Delete", nullable = false, columnDefinition = "INT DEFAULT 0")
	    private int isDelete = 0;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "role_id", referencedColumnName = "id")
	    private RoleEntity role;
	
}
