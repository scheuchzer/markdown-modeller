package com.ja.markdown.modeller.sc.java;

import com.ja.markdown.modeller.sc.java.model.JavaClass;

public enum JdkClass {
	Boolean, String, Character, Class, Byte, Short, Integer, Long, Double, Float, List, Method;

	private static final JavaClassFactory jcf = new JavaClassFactory(null);

	public JavaClass instance() {
		return jcf.resolve(toString());
	}
}
