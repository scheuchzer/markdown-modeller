package com.ja.markdown.modeller.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import lombok.Data;

import com.ja.markdown.modeller.parser.MarkdownParser;
import com.ja.markdown.modeller.parser.spi.SpiMarkdownParser;

@Data
public class Configuration {

	private String source;
	private String encoding = "UTF-8";
	private String parser = SpiMarkdownParser.class.getName();

	public File getSourceFile() {
		return new File(source);
	}

	public InputStream getSourceInputStream() {
		try {
			return new FileInputStream(getSourceFile());
		} catch (final FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public MarkdownParser getMarkdownParser() {
		try {
			return (MarkdownParser) Class.forName(parser).newInstance();
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}
}
