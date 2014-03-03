package com.ja.markdown.modeller.generator;

import com.ja.markdown.modeller.model.ModelException;

public class GeneratorException extends ModelException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GeneratorException() {
		super();
	}

	public GeneratorException(final String message, final Throwable cause,
			final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GeneratorException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public GeneratorException(final String message) {
		super(message);
	}

	public GeneratorException(final Throwable cause) {
		super(cause);
	}

}
