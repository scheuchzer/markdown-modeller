package com.ja.markdown.modeller.core;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.InputStream;

import org.junit.Test;

public class ResourcesTest {

	@Test
	public void testAsInputStream() {

		final InputStream is = Resources.asInputStream(new ResourcesDummy(),
				"mytest.txt");
		assertThat(is, is(notNullValue()));
	}

	@Test
	public void testAsInputStreamNotFound() {

		final InputStream is = Resources.asInputStream(new ResourcesDummy(),
				"foo.txt");
		assertThat(is, is(nullValue()));
	}
}
