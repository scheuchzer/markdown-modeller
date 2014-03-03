package com.ja.markdown.modeller.core;

import java.io.InputStream;

import org.junit.Test;

import com.ja.markdown.modeller.parser.ParseException;

public class WorkflowTest {

	@Test
	public void testExecute() throws ParseException {
		final InputStream source = Resources.asInputStream(this, "model.md");
		final Configuration config = new Configuration() {
			@Override
			public InputStream getSourceInputStream() {
				return source;
			}
		};

		final Workflow workflow = new Workflow();
		workflow.execute(config);
	}
}
