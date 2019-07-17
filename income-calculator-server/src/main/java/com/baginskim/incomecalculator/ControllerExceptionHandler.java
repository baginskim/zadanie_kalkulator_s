package com.baginskim.incomecalculator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
class ControllerExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public final ResponseEntity<Error> handleBadRequest(IllegalArgumentException ex, WebRequest request) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalStateException.class)
	public final ResponseEntity<Error> handleEmptyResponse(IllegalStateException ex, WebRequest request) {
		return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public final ResponseEntity<Error> handleNotFound(HttpClientErrorException ex, WebRequest request) {
		return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
	}

}
