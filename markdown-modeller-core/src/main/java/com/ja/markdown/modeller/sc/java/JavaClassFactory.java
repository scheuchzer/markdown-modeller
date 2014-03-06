package com.ja.markdown.modeller.sc.java;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ja.markdown.modeller.sc.java.model.JavaClass;

public class JavaClassFactory {

	private final Map<String, String> mapping = new HashMap<>();

	private final String targetPackage;

	public JavaClassFactory(final String targetPackage) {
		this.targetPackage = targetPackage;
		mapping.put(String.class.getSimpleName(), String.class.getName());
		mapping.put(Byte.class.getSimpleName(), Byte.class.getName());
		mapping.put(Integer.class.getSimpleName(), Integer.class.getName());
		mapping.put(Long.class.getSimpleName(), Long.class.getName());
		mapping.put(Double.class.getSimpleName(), Double.class.getName());
		mapping.put(Float.class.getSimpleName(), Float.class.getName());
		mapping.put(Character.class.getSimpleName(), Character.class.getName());
		mapping.put(List.class.getSimpleName(), List.class.getName());
	}

	public JavaClass resolve(final String modelClassName) {
		if (modelClassName == null) {
			return null;
		}
		String className = mapping.get(modelClassName);
		if (className == null) {
			className = targetPackage + "." + modelClassName;
		}
		return new JavaClass(className);
	}
}
