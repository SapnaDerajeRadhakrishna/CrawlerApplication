package org.demo.application.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ ResourceNotFoundException.class })
	public ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest request) {
		return new ResponseEntity<Object>("Resource Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Throwable.class)
	public void handleMiscException(Throwable e, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(DefaultErrorAttributes.class.getName() + ".error", null);
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
}