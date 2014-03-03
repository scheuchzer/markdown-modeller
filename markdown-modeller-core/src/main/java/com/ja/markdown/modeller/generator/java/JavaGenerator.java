package com.ja.markdown.modeller.generator.java;

import com.ja.markdown.modeller.generator.GeneratorException;
import com.ja.markdown.modeller.generator.java.stringtemplate.StringTemplateClassGenerator;
import com.ja.markdown.modeller.model.Model;

public class JavaGenerator {

	public void process(final Model model) throws GeneratorException {
		final JavaConfig javaConfig = new JavaConfig();
		javaConfig.setProjectPackage("foo");
		final JavaModel javaModel = new JavaModel(model);

		new DomainName2JavaClassEnhancer(javaConfig).enhance(javaModel, "pojo");
		new LombokEnhancer().enhance(javaModel);
		// JPA enhancer
		// JaxRS enhancer
		new ImportsEnhancer().enhance(javaModel);
		final StringTemplateClassGenerator classGenerator = new StringTemplateClassGenerator();
		for (final JavaModelClass jmc : javaModel.getJavaMasterDataClasses()) {
			final String classCode = classGenerator.generateClass(jmc);
			System.out.println(classCode);
		}
	}
}
