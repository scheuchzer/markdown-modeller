package com.ja.markdown.modeller.sc.java;

import java.util.Collection;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import com.ja.markdown.modeller.sc.java.model.JavaAnnotation;
import com.ja.markdown.modeller.sc.java.model.JavaClass;
import com.ja.markdown.modeller.sc.java.model.JavaMember;
import com.ja.markdown.modeller.sc.java.model.JavaMethod;
import com.ja.markdown.modeller.sc.java.model.JavaMethodParameter;
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
		for (final JavaClass usedClass : jc.getUsedClasses()) {
			resolveImports(usedClass, jc);
		}
		resolveAnnotationImports(jc.getAnnotations(), jc);
		resolveMemberImports(jc.getMembers(), jc);
		resolveMethodImports(jc.getMethods(), jc);
		resolveImports(jc.getExtendsType(), jc);

	}

	private void resolveMethodImports(final List<JavaMethod> methods,
			final JavaClass owner) {
		for (final JavaMethod jm : methods) {
			resolveImports(jm, owner);
		}

	}

	private void resolveImports(final JavaMethod jm, final JavaClass owner) {
		resolveAnnotationImports(jm.getAnnotations(), owner);
		resolveImports(jm.getReturnType(), owner);
		for (final JavaMethodParameter jmp : jm.getParameters()) {
			resolveImports(jmp.getType(), owner);
			resolveAnnotationImports(jmp.getAnnotations(), owner);
		}
	}

	private void resolveMemberImports(final Collection<JavaMember> javaMembers,
			final JavaClass owner) {
		for (final JavaMember jm : javaMembers) {
			resolveAnnotationImports(jm.getAnnotations(), owner);
			resolveImports(jm.getClassType(), owner);
			resolveImports(jm.getClassType().getGenericType(), owner);
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
		if ("?".equals(jc.getName())) {
			return;
		}
		log.info("import class: {}", jc.getName());
		jc.setImported(true);
		owner.importClass(jc);
		resolveImports(jc.getGenericType(), owner);

	}
}
