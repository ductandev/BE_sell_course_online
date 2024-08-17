package vn.io.ductandev.course.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseDTO<T> {
    String message;
    int statusCode;
    List<T> content;
    T object;
    Date date;

    public ResponseDTO(String message, int statusCode, List<T> content, Date date) {
        this.message = message;
        this.statusCode = statusCode;
        this.content = content;
        this.date = date;
    }

	public ResponseDTO(String message, int statusCode, T object, Date date) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.object = object;
		this.date = date;
	}
    
    
}
