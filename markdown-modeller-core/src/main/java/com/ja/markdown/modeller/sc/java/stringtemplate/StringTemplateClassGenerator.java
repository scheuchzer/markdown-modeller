package com.ja.markdown.modeller.sc.java.stringtemplate;

import lombok.extern.slf4j.Slf4j;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;

import com.ja.markdown.modeller.sc.java.JavaModelPlugin;
import com.ja.markdown.modeller.sc.java.model.JavaClass;
import com.ja.markdown.modeller.sc.java.model.JavaProject;

@Slf4j
public class StringTemplateClassGenerator implements JavaModelPlugin {

	@Override
	public void execute(final JavaProject project) {
		for (final JavaClass jc : project.getJavaModel().getClasses()) {
			final String sc = generateClass(jc);
			jc.setSourceCode(sc);
		}
	}

	public String generateClass(final JavaClass jc) {
		log.info("Creating code for class: {}", jc.getName());
		/*
		 * Important: The template dir MUSTN'T exisist on the test class path.
		 * STGroupDir only tries the first location found on the classpath!
		 */
		final STGroup group = new STGroupDir(getClass().getPackage().getName()
				.replace(".", "/")
				+ "/template");
		final ST st = group.getInstanceOf("class");
		st.add("javaClass", jc);
		final String code = st.render();
		log.debug("Generated code:\n{}", code);
		return code;
	}

}
