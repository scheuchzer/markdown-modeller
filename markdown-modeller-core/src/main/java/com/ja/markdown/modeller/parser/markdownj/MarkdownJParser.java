package com.ja.markdown.modeller.parser.markdownj;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import lombok.extern.slf4j.Slf4j;

import org.markdownj.MarkdownProcessor;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.ja.markdown.modeller.parser.MarkdownParser;
import com.ja.markdown.modeller.parser.ParseException;

@Slf4j
public class MarkdownJParser implements MarkdownParser {

	private static final String DEFAULT_CHARSET = "UTF-8";

	private final DocumentBuilderFactory factory = DocumentBuilderFactory
			.newInstance();

	public Document parse(final String markdown)
			throws UnsupportedEncodingException, IOException, ParseException {
		return parse(
				new ByteArrayInputStream(markdown.getBytes(DEFAULT_CHARSET)),
				DEFAULT_CHARSET);
	}

	@Override
	public Document parse(final InputStream inputStream, final String charset)
			throws IOException, ParseException {

		try (InputStream is = inputStream;
				Scanner scanner = new Scanner(is, charset)) {
			scanner.useDelimiter("\\A");
			final String markdown = scanner.hasNext() ? scanner.next() : "";
			log.debug("markdown={}", markdown);
			final MarkdownProcessor markdownProcessor = new MarkdownProcessor();
			final StringBuilder html = new StringBuilder();
			html.append("<markdownModeller>");
			html.append(markdownProcessor.markdown(markdown));
			html.append("</markdownModeller>");
			log.debug("html={}", html);
			final DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(new InputSource(new StringReader(html
					.toString())));
		} catch (final SAXException | ParserConfigurationException e) {
			log.debug("Parsing failed.", e);
			throw new ParseException(e);
		}
	}
}
