package com.ja.markdown.modeller.sc.java;

import com.ja.markdown.modeller.sc.java.model.JavaClass;

public enum JdkClass {
	String, Character, Byte, Short, Integer, Long, Double, Float, List;

	private static final JavaClassFactory jcf = new JavaClassFactory(null);

	public JavaClass instance() {
		return jcf.resolve(toString());
	}
}
