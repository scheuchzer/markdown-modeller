package com.ja.markdown.modeller.sc.java.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
public class JavaMethodParameter extends JavaBase {

	private final String name;

	private final JavaClass type;

	private List<JavaAnnotation> annotations = new ArrayList<>();

	public void add(final JavaAnnotation annotation) {
		annotations.add(annotation);
	}
}
