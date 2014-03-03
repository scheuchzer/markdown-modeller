package com.ja.markdown.modeller.generator.java;

import com.ja.markdown.modeller.model.ModelClass;

public class CommonJavaClass extends JavaModelClass {

	public CommonJavaClass(final String name, final String pkg) {
		super(new ModelClass(name));
		setJavaClassName(String.format("%s.%s", pkg, name));
	}

	public CommonJavaClass(final Class<?> clazz) {
		super(new ModelClass(clazz.getSimpleName()));
		setJavaClassName(clazz.getName());
	}

}
