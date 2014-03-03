package com.ja.markdown.modeller.generator.java;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JavaLangClass extends CommonJavaClass {

	private final boolean defaultImport = true;

	public JavaLangClass(final String name) {
		super(name, "java.lang");
	}

}
