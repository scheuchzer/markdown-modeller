package com.ja.markdown.modeller.sc.java.cdi;

import com.ja.markdown.modeller.sc.java.model.JavaClass;

public enum CdiClass {

	Inject;

	public JavaClass instance() {
		return new JavaClass("javax.inject." + toString());
	}
}
