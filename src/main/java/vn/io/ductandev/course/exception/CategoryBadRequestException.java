package vn.io.ductandev.course.exception;

public class CategoryBadRequestException  extends RuntimeException{
	public CategoryBadRequestException(int id, String name) {
		super("bad request : " + name);
	}
}
