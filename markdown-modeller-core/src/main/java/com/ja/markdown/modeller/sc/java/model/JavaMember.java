package com.ja.markdown.modeller.sc.java.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import org.apache.commons.lang3.StringUtils;

import com.ja.markdown.modeller.model.ModelClassMember;

@Data
public class JavaMember {

	private String name;
	private JavaClass classType;
	private boolean withSetter;
	private boolean withGetter;

	private final List<JavaAnnotation> annotations = new ArrayList<>();
	private final List<JavaMethod> methods = new ArrayList<>();
	private final List<JavaMember> members = new ArrayList<>();
	private final ModelClassMember modelClassMember;

	public JavaMember(final ModelClassMember mcm) {
		this.modelClassMember = mcm;
		this.name = mcm.getName();
	}

	public JavaMember(final String name) {
		this.modelClassMember = null;
		this.name = name;
	}

	public boolean isGeneric() {
		return classType.isGeneric();
	}

	public String getNameForMethod() {
		return StringUtils.capitalize(name);
	}

	public void add(final JavaAnnotation annotation) {
		annotations.add(annotation);
	}
}
