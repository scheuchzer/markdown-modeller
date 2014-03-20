package com.ja.markdown.modeller.sc.java.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class JavaAnnotation {

	private final JavaClass classType;
	private String value;
	private Map<String, Object> properties = new HashMap<>();

	public JavaAnnotation(final JavaClass classType) {
		this.classType = classType;
	}

	public void addStringProperty(final String name, final String value) {
		properties.put(name, String.format("\"%s\"", value));
	}

	public void addIntProperty(final String name, final int value) {
		properties.put(name, Integer.toString(value));
	}

	public String getProperties() {
		if (value != null) {
			return String.format("\"%s\"", value);
		}
		final StringBuilder sb = new StringBuilder();
		for (final Map.Entry<String, Object> entry : properties.entrySet()) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(String.format("%s = %s", entry.getKey(), entry.getValue()));
		}
		return sb.length() == 0 ? null : sb.toString();
	}
}
