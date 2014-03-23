package com.ja.markdown.modeller.sc.java.rest;

import com.ja.markdown.modeller.sc.java.model.JavaClass;

public enum JaxRsClass {
	Application("core"), ApplicationPath, GET, Path, PathParam;

	private String subPackage;

	private JaxRsClass() {

	}

	private JaxRsClass(final String subPackage) {
		this.subPackage = subPackage;
	}

	public JavaClass instance() {
		return new JavaClass("javax.ws.rs." + toString());
	}

	@Override
	public String toString() {
		return subPackage == null ? super.toString() : subPackage + "."
				+ super.toString();
	}
}