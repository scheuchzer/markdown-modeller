package com.ja.markdown.modeller.parser.spi;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.w3c.dom.Document;

import com.ja.markdown.modeller.parser.ParseException;

public class SpiMarkdownParserTest {
	@Test
	public void testParse() throws ParseException, IOException {
		final InputStream inputStream = new ByteArrayInputStream(
				"# TEST".getBytes());
		final Document doc = new SpiMarkdownParser()
				.parse(inputStream, "UTF-8");
		assertThat(doc, is(notNullValue()));
	}
}
