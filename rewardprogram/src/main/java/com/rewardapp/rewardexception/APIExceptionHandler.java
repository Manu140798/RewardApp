package com.rewardapp.rewardexception;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*Global exception handler for REST API controllers.

	Handles specific exceptions like CustomerNotFoundException by returning

	404 Not Found response, and catches all other exceptions to return

	400 Bad Request response with a generic error message.*/
@RestControllerAdvice
public class APIExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)

	public ResponseEntity<String> handleCustomerNF (CustomerNotFoundException ex) {

	return ResponseEntity.status (HttpStatus.NOT_FOUND).body(ex.getMessage());

	}

	@ExceptionHandler (Exception.class)

	public ResponseEntity<String> handleAll(Exception ex){

	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: "+ex.getMessage());

}
}
