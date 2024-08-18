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
    List<T> data;
    T object;
    boolean isSuccess;
    Date date;

    // trả về 1 array object
    public ResponseDTO(String message, int statusCode, List<T> data, Date date) {
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
        this.date = date;
    }

    // trả về 1 object
	public ResponseDTO(String message, int statusCode, T object, Date date) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.object = object;
		this.date = date;
	}

    // Trả về 1 object boolean
    public ResponseDTO(String message, int statusCode, boolean isSuccess, Date date) {
        this.message = message;
        this.statusCode = statusCode;
        this.isSuccess = isSuccess; // Ép kiểu boolean thành T
        this.date = date;
    }
}
