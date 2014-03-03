package com.ja.markdown.modeller.generator.java;

import java.util.List;

import com.ja.markdown.modeller.generator.GeneratorException;

class JavaClassMemberMapper {

	private final NameMap names;

	JavaClassMemberMapper(final NameMap names) {
		this.names = names;

	}

	void map(final JavaModel model) throws GeneratorException {
		map(model.getJavaBusinessObjectClasses());
		map(model.getJavaMasterDataClasses());
	}

	void map(final List<JavaModelClass> modelClasses) throws GeneratorException {
		for (final JavaModelClass mc : modelClasses) {
			map(mc);
		}

	}

	void map(final JavaModelClass mc) throws GeneratorException {
		for (final JavaModelClassMember m : mc.getJavaMembers()) {
			map(m);
		}
	}

	void map(final JavaModelClassMember m) throws GeneratorException {
		m.setJavaType(names.map(m.getType()));
		if (m.isGeneric()) {
			m.setJavaGenericType(names.map(m.getGenericType()));
		}
	}

}
