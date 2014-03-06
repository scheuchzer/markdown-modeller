package com.ja.markdown.modeller.sc.java.model;

import lombok.Data;

@Data
public class JavaAnnotation {

	private final JavaClass classType;

	public JavaAnnotation(final JavaClass classType) {
		this.classType = classType;

	}
}
