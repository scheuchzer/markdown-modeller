package com.ja.markdown.modeller.model;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.w3c.dom.Document;

import com.ja.markdown.modeller.core.Resources;
import com.ja.markdown.modeller.model.processing.DomainModelFactory;
import com.ja.markdown.modeller.parser.ParseException;
import com.ja.markdown.modeller.parser.markdownj.MarkdownJParser;

public class DomainModelFactoryTest {

	@Test
	public void testCreate() throws IOException, ParseException {
		final InputStream source = Resources.asInputStream(this, "model.md");

		final Document doc = new MarkdownJParser().parse(source, "UTF-8");

		final DomainModel model = new DomainModelFactory().create(doc);

		System.out.println(model);
	}
}
