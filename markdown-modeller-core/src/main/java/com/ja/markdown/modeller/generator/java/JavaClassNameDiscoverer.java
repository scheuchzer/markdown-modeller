package com.ja.markdown.modeller.generator.java;

import java.util.List;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import com.ja.markdown.modeller.generator.GeneratorException;

@Slf4j
@Value
public class JavaClassNameDiscoverer {

	private final JavaConfig config;

	private final NameMap nameMap = new NameMap();

	JavaClassNameDiscoverer(final JavaConfig config) {
		this.config = config;

	}

	void discover(final List<JavaModelClass> modelClasses, final String module)
			throws GeneratorException {
		for (final JavaModelClass mc : modelClasses) {
			final String javaClassName = String.format("%s.%s.%s",
					config.getProjectPackage(), module, mc.getName());
			log.info("Java Class: {}", javaClassName);
			mc.setJavaClassName(javaClassName);
			nameMap.add(mc);
		}
	}
}
