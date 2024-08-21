package vn.io.ductandev.course.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserCourseId implements Serializable {
	private Long user;
	private Long course;

	// Default constructor
	public UserCourseId() {}

	// Parameterized constructor
	public UserCourseId(Long user, Long course) {
		this.user = user;
		this.course = course;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserCourseId that = (UserCourseId) o;
		return Objects.equals(user, that.user) && Objects.equals(course, that.course);
	}

	@Override
	public int hashCode() {
		return Objects.hash(user, course);
	}

	// Getters and setters
	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public Long getCourse() {
		return course;
	}

	public void setCourse(Long course) {
		this.course = course;
	}
}
