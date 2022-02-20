package com.sanjib.cart.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RestControllerAdvice
public class AppGlobalExceptionHandeller extends ResponseEntityExceptionHandler {
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		details = ex.getFieldErrors().stream().map(e1->e1.getField()+" : "+e1.getDefaultMessage()).collect(Collectors.toList());
		
		ValidationErrorResponse error=new ValidationErrorResponse(4000L, details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}//handleMethodArgumentNotValid(---)
	
	/*
	 * @ExceptionHandler(Exception.class) public final ResponseEntity<ApiError>
	 * handleAllException(Exception ex, WebRequest request){ String
	 * errorMessage=ex.getLocalizedMessage(); ApiError error=new ApiError(5000L,
	 * errorMessage , LocalDateTime.now()); return new
	 * ResponseEntity<>(error,HttpStatus.BAD_REQUEST); }//handleException()
	 */	
	
	/*@ExceptionHandler(ServiceDownException.class)
	public final ResponseEntity<ApiError> handleServiceDownException(ServiceDownException ex, WebRequest request){
		String errorMessage=ex.getLocalizedMessage();
		ApiError error=new ApiError(7000L, errorMessage, LocalDateTime.now());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}//handleInsufficientBalanceException(--)
	*/
}