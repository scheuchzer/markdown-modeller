package com.ja.markdown.modeller.sc.java.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
public class JavaMethod extends JavaBase {

	private final String name;
	private JavaClass returnType;
	private ContentProvider contentProvider = ContentProvider.nullContentProvider;
	private String content;
	private List<JavaAnnotation> annotations = new ArrayList<>();
	private List<JavaMethodParameter> parameters = new ArrayList<>();
	private List<JavaClass> usedClasses = new ArrayList<>();

	public String getContent() {
		return content == null ? contentProvider.getContent() : content;
	}

	public void add(final JavaAnnotation annotation) {
		annotations.add(annotation);
	}

	public void add(final JavaMethodParameter parameter) {
		parameters.add(parameter);
	}

	public void addUsedClass(final JavaClass jc) {
		usedClasses.add(jc);
	}
}
