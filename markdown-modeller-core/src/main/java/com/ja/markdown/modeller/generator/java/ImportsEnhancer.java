package com.ja.markdown.modeller.generator.java;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImportsEnhancer {

	public void enhance(final JavaModel model) {
		resolveImports(model.getJavaMasterDataClasses());
		resolveImports(model.getJavaBusinessObjectClasses());
	}

	private void resolveImports(final List<JavaModelClass> classes) {
		for (final JavaModelClass jmc : classes) {
			resolveImports(jmc);
		}

	}

	private void resolveImports(final JavaModelClass jmc) {
		final Set<JavaModelClass> imports = new HashSet<>();
		resolveImports(imports, jmc.getClassAnnotations(), jmc);
		resolveImports(imports, jmc.getJavaMembers(), jmc);

	}

	private void resolveImports(final Set<JavaModelClass> imports,
			final List<JavaModelClassMember> javaMembers,
			final JavaModelClass owner) {
		for (final JavaModelClassMember jmcm : javaMembers) {
			resolveImports(imports, jmcm.getJavaType(), owner);
			resolveImports(imports, jmcm.getJavaGenericType(), owner);
		}

	}

	private void resolveImports(final Set<JavaModelClass> imports,
			final Set<JavaModelClass> classes, final JavaModelClass owner) {
		for (final JavaModelClass jmc : classes) {
			resolveImports(imports, jmc, owner);
		}

	}

	private void resolveImports(final Set<JavaModelClass> imports,
			final JavaModelClass jmc, final JavaModelClass owner) {
		if (jmc == null || imports.contains(jmc)) {
			return;
		}
		log.info("Class {}, import class: {}", owner.getJavaClassName(),
				jmc.getJavaClassName());
		jmc.setImported(true);
		owner.importClass(jmc);
		imports.add(jmc);

	}
}
