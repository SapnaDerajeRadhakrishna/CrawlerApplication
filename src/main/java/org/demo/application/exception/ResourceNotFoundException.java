package org.demo.application.exception;

public class ResourceNotFoundException extends CrawlerBaseException {

	private static final long serialVersionUID = -3184123950098862815L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceNotFoundException(Throwable cause) {
		super(cause);
	}

}
