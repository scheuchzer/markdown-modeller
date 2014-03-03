package com.ja.markdown.modeller.generator.java;

public enum JdkClass {

	String(new JavaLangClass("String")), Integer(new JavaLangClass("Integer")), Long(
			new JavaLangClass("Long")), Double(new JavaLangClass("Double")), Float(
			new JavaLangClass("Float")), Short(new JavaLangClass("Short")), Byte(
			new JavaLangClass("Byte")), Character(
			new JavaLangClass("Character")), Boolean(new JavaLangClass(
			"Boolean")), List(new JavaUtilClass("List"));

	private final JavaModelClass jmc;

	private JdkClass(final JavaModelClass jmc) {
		this.jmc = jmc;

	}

	public JavaModelClass getModelClass() {
		final String className = jmc.getName();
		final Class<?> clazz = jmc.getClass();
		try {
			return (JavaModelClass) clazz.getConstructor(String.class)
					.newInstance(className);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}
}
