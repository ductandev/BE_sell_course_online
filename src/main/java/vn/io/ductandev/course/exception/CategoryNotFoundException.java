package vn.io.ductandev.course.exception;

public class CategoryNotFoundException extends RuntimeException {

	public CategoryNotFoundException(int id) {
		super();
	}
	
//	 private final ErrorCode errorCode;
//
//	    public CategoryNotFoundException(int id) {
//	        super(ErrorCode.CATEGORY_NOT_FOUND.getCode() + ": " + id);
//	        this.errorCode = ErrorCode.CATEGORY_NOT_FOUND;
//	    }
//
//	    public CategoryNotFoundException(ErrorCode errorCode) {
//	        super(errorCode.getCode());
//	        this.errorCode = errorCode;
//	    }
//
//	    public ErrorCode getErrorCode() {
//	        return errorCode;
//	    }
	
	
}
