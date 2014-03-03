package com.ja.markdown.modeller.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.w3c.dom.Document;

import com.ja.markdown.modeller.parser.MarkdownParser;
import com.ja.markdown.modeller.parser.ParseException;

public class Workflow {

	public void execute(final Configuration config) throws ParseException {

		final InputStream source = config.getSourceInputStream();
		final MarkdownParser parser = config.getMarkdownParser();

		try {
			final Document doc = parser.parse(source, config.getEncoding());

		} catch (final FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
}
