package com.ja.markdown.modeller.generator.java;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ja.markdown.modeller.generator.GeneratorException;

class NameMap {

	private final Map<String, JavaModelClass> map = new HashMap<>();

	NameMap() {
		for (final JdkClass c : JdkClass.values()) {
			final JavaModelClass jmc = c.getModelClass();
			map.put(jmc.getName(), jmc);
		}
	}

	void add(final List<JavaModelClass> modelClasses) throws GeneratorException {
		for (final JavaModelClass mc : modelClasses) {
			add(mc);
		}
	}

	void add(final JavaModelClass mc) throws GeneratorException {
		if (map.containsKey(mc.getName())) {
			throw new GeneratorException("Duplicate entry: " + mc.getName());
		}
		map.put(mc.getName(), mc);
	}

	JavaModelClass map(final String domainModelClassName)
			throws GeneratorException {
		final JavaModelClass mc = map.get(domainModelClassName);
		if (mc == null) {
			throw new GeneratorException(
					"No java class found for domain model name: "
							+ domainModelClassName);
		}
		return mc;
	}
}
