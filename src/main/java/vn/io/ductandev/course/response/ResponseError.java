package vn.io.ductandev.course.response;

import java.util.Date;

import lombok.Data;

@Data
public class ResponseError {
	 private String message;
     private int statusCode;
     private Date timestamp;

     public ResponseError(String message, int statusCode, Date timestamp) {
         this.message = message;
         this.statusCode = statusCode;
         this.timestamp = timestamp;
     }

}
