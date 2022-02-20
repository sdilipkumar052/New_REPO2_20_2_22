package com.sanjib.cart.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ValidationErrorResponse {
	
	private Long errorCode;
	
	private List<String> errorMessage;

}
