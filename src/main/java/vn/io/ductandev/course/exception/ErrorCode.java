package vn.io.ductandev.course.exception;

public enum ErrorCode {

	CATEGORY_NOT_FOUND("not found category"),
	CATEGORY_BAD_REQUEST("bad request category");
	
	private final String code;
	
	private ErrorCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
	
	
}
