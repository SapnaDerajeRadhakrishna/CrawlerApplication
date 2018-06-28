package org.demo.application.exception;

public class CrawlerBaseException extends Exception {

	private static final long serialVersionUID = -7256705601853116677L;

	public CrawlerBaseException(String message) {
		super(message);
	}

	public CrawlerBaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public CrawlerBaseException(Throwable cause) {
		super(cause);
	}

}
