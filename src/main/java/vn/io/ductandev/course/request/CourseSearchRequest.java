package vn.io.ductandev.course.request;

import java.util.Date;

import org.springframework.data.domain.Page;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseSearchRequest<T> {

	String message;
	int statusCode;
	Page<T> data;
	Date date;
	public CourseSearchRequest(String message, int statusCode, Page<T> data, Date date) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.data = data;
		this.date = date;
	}
	
	
}
