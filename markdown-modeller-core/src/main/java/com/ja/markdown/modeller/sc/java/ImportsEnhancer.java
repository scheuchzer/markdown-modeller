package com.ja.markdown.modeller.sc.java;

import java.util.Collection;

import lombok.extern.slf4j.Slf4j;

import com.ja.markdown.modeller.sc.java.model.JavaAnnotation;
import com.ja.markdown.modeller.sc.java.model.JavaClass;
import com.ja.markdown.modeller.sc.java.model.JavaMember;
import com.ja.markdown.modeller.sc.java.model.JavaModel;
import com.ja.markdown.modeller.sc.java.model.JavaProject;

@Slf4j
public class ImportsEnhancer implements JavaModelPlugin {

	@Override
	public void execute(final JavaProject project) {
		enhance(project.getJavaModel());

	}

	public void enhance(final JavaModel model) {
		for (final JavaClass jmc : model.getClasses()) {
			resolveImports(jmc);
		}
	}

	private void resolveImports(final JavaClass jc) {
		resolveAnnotationImports(jc.getAnnotations(), jc);
		resolveMemberImports(jc.getMembers(), jc);

	}

	private void resolveMemberImports(final Collection<JavaMember> javaMembers,
			final JavaClass owner) {
		for (final JavaMember jm : javaMembers) {
			resolveAnnotationImports(jm.getAnnotations(), owner);
			resolveImports(jm.getClassType(), owner);
			resolveImports(jm.getGenericType(), owner);
		}

	}

	private void resolveAnnotationImports(
			final Collection<JavaAnnotation> classes, final JavaClass owner) {
		for (final JavaAnnotation jc : classes) {
			resolveImports(jc.getClassType(), owner);
		}

	}

	private void resolveImports(final JavaClass jc, final JavaClass owner) {
		if (jc == null) {
			return;
		}
		log.info("import class: {}", jc.getName());
		jc.setImported(true);
		owner.importClass(jc);

	}

}
