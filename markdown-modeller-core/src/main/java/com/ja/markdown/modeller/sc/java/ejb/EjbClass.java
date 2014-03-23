package com.ja.markdown.modeller.sc.java.ejb;

import com.ja.markdown.modeller.sc.java.model.JavaClass;

public enum EjbClass {
	Singleton, Startup, Stateless;

	public JavaClass instance() {
		return new JavaClass("javax.ejb." + toString());
	}
}