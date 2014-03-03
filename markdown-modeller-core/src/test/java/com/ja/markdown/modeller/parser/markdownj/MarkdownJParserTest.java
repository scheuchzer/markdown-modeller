package com.ja.markdown.modeller.parser.markdownj;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;
import org.w3c.dom.Document;

import com.ja.markdown.modeller.parser.MarkdownParser;
import com.ja.markdown.modeller.parser.ParseException;

public class MarkdownJParserTest {

	@Test
	public void testParseFromString() throws IOException, ParseException {
		final String markdown = "#Project Title\n" + "##Domain Model\n"
				+ "###Master Data\n" + "###Business Objects\n";
		final MarkdownJParser parser = new MarkdownJParser();
		final Document doc = parser.parse(markdown);
		assertThat(doc, is(notNullValue()));
		assertThat(doc.getFirstChild().getNodeName(),
				is(MarkdownParser.ROOT_TAG));
	}
}
