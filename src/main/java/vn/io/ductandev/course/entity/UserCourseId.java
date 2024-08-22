package vn.io.ductandev.course.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;


public class UserCourseId implements Serializable {
	private int user;
	private int course;

	// Default constructor
	public UserCourseId() {}

	// Parameterized constructor
	public UserCourseId(int user, int course) {
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

	
}
