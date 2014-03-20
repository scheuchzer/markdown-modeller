package com.ja.markdown.modeller.sc.java.maven;

import com.ja.markdown.modeller.sc.java.maven.model.MavenPlugin;

public enum Plugin {

	compiler("org.apache.maven.plugins:maven-compiler-plugin:3.1"), war(
			"org.apache.maven.plugins:maven-war-plugin:2.4");

	private String definition;

	private Plugin(final String definition) {
		this.definition = definition;
	}

	public MavenPlugin instance() {
		return MavenPlugin.create(definition);
	}
}
