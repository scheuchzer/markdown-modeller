package com.ja.markdown.modeller.sc.java.maven.model;

import lombok.Data;

@Data
public class Resource {

	private final String type;
	private final String name;

	private Resource(final String type, final String name) {
		this.type = type;
		this.name = name;
	}

	public static Resource folder(final String outputFolder) {
		return new Resource("directory", outputFolder);
	}

}
