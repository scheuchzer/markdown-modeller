package com.ja.markdown.modeller.generator.java;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.ja.markdown.modeller.generator.GeneratorException;
import com.ja.markdown.modeller.model.ModelClass;

public class NameMapTest {

	@Test
	public void testAdd() throws GeneratorException {
		final NameMap map = new NameMap();
		final JavaModelClass mc = new JavaModelClass(new ModelClass("foo"));
		map.add(mc);
	}

	@Test(expected = GeneratorException.class)
	public void testAddDuplicate() throws GeneratorException {
		final NameMap map = new NameMap();
		final JavaModelClass mc = new JavaModelClass(new ModelClass("foo"));
		map.add(mc);
		map.add(mc);
		fail();
	}

	@Test
	public void testMap() throws GeneratorException {
		final NameMap map = new NameMap();
		final JavaModelClass expected = new JavaModelClass(
				new ModelClass("foo"));
		map.add(expected);
		final JavaModelClass actual = map.map("foo");
		assertThat(actual, is(expected));
	}

	@Test(expected = GeneratorException.class)
	public void testMapUnknown() throws GeneratorException {
		final NameMap map = new NameMap();
		final JavaModelClass expected = new JavaModelClass(
				new ModelClass("foo"));
		map.add(expected);
		map.map("bar");
		fail();
	}
}
