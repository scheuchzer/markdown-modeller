package com.ja.markdown.modeller.model;

public class ModelException extends Exception {

	private static final long serialVersionUID = 1L;

	public ModelException() {
		super();
	}

	public ModelException(final String message, final Throwable cause,
			final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ModelException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ModelException(final String message) {
		super(message);
	}

	public ModelException(final Throwable cause) {
		super(cause);
	}

}
