package com.ja.markdown.modeller.generator.java;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ja.markdown.modeller.generator.GeneratorException;
import com.ja.markdown.modeller.model.ModelClass;

public class JavaClassNameDiscovererTest {

	@Test
	public void testDiscover() throws GeneratorException {
		final JavaConfig javaConfig = new JavaConfig();
		javaConfig.setProjectPackage("com.ja.example");

		final List<JavaModelClass> classes = new ArrayList<>();
		final JavaModelClass mc = new JavaModelClass(new ModelClass("MyClass"));
		classes.add(mc);

		final JavaClassNameDiscoverer discoverer = new JavaClassNameDiscoverer(
				javaConfig);
		discoverer.discover(classes, "test");

		assertThat(mc.getJavaClassName(), is("com.ja.example.test.MyClass"));
	}
}
