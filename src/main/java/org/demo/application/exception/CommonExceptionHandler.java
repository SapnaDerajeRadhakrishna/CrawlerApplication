package org.demo.application.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonExceptionHandler {

	private static final String ERROR = ".ERROR";

	@ExceptionHandler(CrawlerBaseException.class)
	void handleTesaException(CrawlerBaseException e, HttpServletRequest request, HttpServletResponse response) {
		log.error("Internal Server Exception received from ", e);
		request.setAttribute(DefaultErrorAttributes.class.getName() + ERROR, null);
		sendHttpError(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Throwable.class)
	void handleMiscException(Throwable e, HttpServletRequest request, HttpServletResponse response) {
		log.error("Throwable received ", e);
		request.setAttribute(DefaultErrorAttributes.class.getName() + ERROR, null);
		sendHttpError(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	protected void sendHttpError(HttpServletResponse response, HttpStatus code) {
		response.setStatus(code.value());
	}
}
