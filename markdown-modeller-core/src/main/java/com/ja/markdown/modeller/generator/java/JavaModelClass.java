package com.ja.markdown.modeller.generator.java;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.apache.commons.lang3.StringUtils;

import com.ja.markdown.modeller.model.ModelClass;
import com.ja.markdown.modeller.model.ModelClassMember;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JavaModelClass extends ModelClass {

	private boolean imported;

	private String javaClassName;

	private Set<JavaModelClass> classAnnotations = new HashSet<>();

	private Set<JavaModelClass> imports = new HashSet<>();

	private Set<JavaModelClassMember> getters = new HashSet<>();
	private Set<JavaModelClassMember> setters = new HashSet<>();

	public JavaModelClass(final ModelClass modelClass) {
		super(modelClass.getName());
		setExceptions(modelClass.getExceptions());

		for (final ModelClassMember mcm : modelClass.getMembers()) {
			add(new JavaModelClassMember(mcm));
		}
	}

	public void add(final JavaModelClassMember member) {
		super.add(member);
		getters.add(member);
		setters.add(member);
	}

	@SuppressWarnings("unchecked")
	public List<JavaModelClassMember> getJavaMembers() {
		final Object o = super.getMembers();
		return (List<JavaModelClassMember>) o;
	}

	public void addClassAnnotation(final JavaModelClass jcm) {
		classAnnotations.add(jcm);
	}

	public String getJavaClassNameForTemplate() {
		return imported ? StringUtils.substringAfterLast(javaClassName, ".")
				: javaClassName;
	}

	public void importClass(final JavaModelClass jmc) {
		imports.add(jmc);
		jmc.setImported(true);
	}

	public String getPackage() {
		return StringUtils.substringBeforeLast(javaClassName, ".");
	}
}
