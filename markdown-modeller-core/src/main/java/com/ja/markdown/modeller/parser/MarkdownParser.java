package com.ja.markdown.modeller.parser;

import java.io.IOException;
import java.io.InputStream;

import org.w3c.dom.Document;

public interface MarkdownParser {

	String ROOT_TAG = "markdownModeller";

	Document parse(final InputStream inputStream, final String charset)
			throws ParseException, IOException;
}
