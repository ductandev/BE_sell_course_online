package vn.io.ductandev.course.dto;

import lombok.Data;

@Data
public class RevenueResponseDTO {

	 private String monthYear;
	 private Float totalRevenue;
	 
	public RevenueResponseDTO(String monthYear, Float totalRevenue) {
		super();
		this.monthYear = monthYear;
		this.totalRevenue = totalRevenue;
	}
	
	public RevenueResponseDTO() {
		// TODO Auto-generated constructor stub
	}
	    
}
