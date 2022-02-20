package com.sanjib.cart.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiError {
	
	private Long errorCode;
	
	private String errorMesssgae;
	
	private LocalDateTime dateAndTime;
	

}//class
