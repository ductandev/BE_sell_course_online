package vn.io.ductandev.course.entity;

import java.io.Serializable;
import java.util.Objects;

public class PersonCourseId implements Serializable {
	
	  	private int person;
	    private int course;
	    
	    public PersonCourseId() {}

	    public PersonCourseId(int person, int course) {
	        this.person = person;
	        this.course = course;
	    }
	    
	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        PersonCourseId that = (PersonCourseId) o;
	        return Objects.equals(person, that.person) && Objects.equals(course, that.course);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(person, course);
	    }

}
