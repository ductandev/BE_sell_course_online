package vn.io.ductandev.course.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import vn.io.ductandev.course.response.ResponseError;

@RestControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(value = { CategoryNotFoundException.class})
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public ResponseError handleCategoryNotFoundException(CategoryNotFoundException ex) {
	            ResponseError errorResponse = new ResponseError(
	            		ErrorCode.CATEGORY_NOT_FOUND.getCode(),
	                    HttpStatus.NOT_FOUND.value(),
	                    new Date()
	            );
	            return errorResponse;
	    }

	    @ExceptionHandler(value = { IllegalArgumentException.class})
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public ResponseError handleCategoryBadRequestException(Exception ex) {
	        ResponseError errorResponse = new ResponseError(
	        		ErrorCode.CATEGORY_BAD_REQUEST.getCode(),
	                HttpStatus.BAD_REQUEST.value(),
	                new Date()
	        );
	        return errorResponse;
	    }
	
	
}
