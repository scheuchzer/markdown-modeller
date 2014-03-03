package com.ja.markdown.modeller.generator.java.stringtemplate;

import lombok.extern.slf4j.Slf4j;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;

import com.ja.markdown.modeller.generator.java.JavaModelClass;

@Slf4j
public class StringTemplateClassGenerator {

	public String generateClass(final JavaModelClass jmc) {
		log.info("Creating code for class: {}", jmc.getJavaClassName());
		/*
		 * Important: The template dir MUSTN'T exisist on the test class path.
		 * STGroupDir only tries the first location found on the classpath!
		 */
		final STGroup group = new STGroupDir(getClass().getPackage().getName()
				.replace(".", "/")
				+ "/template");
		final ST st = group.getInstanceOf("class");
		st.add("modelClass", jmc);
		final String code = st.render();
		log.debug("Generated code:\n{}", code);
		return code;
	}
}
