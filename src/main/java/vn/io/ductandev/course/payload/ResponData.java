package vn.io.ductandev.course.payload;

import lombok.Data;

@Data
public class ResponData {
	private int status = 200;
	private String desc;
	private Object data;
	private boolean isSuccess = true;
	
	private String username;
}
