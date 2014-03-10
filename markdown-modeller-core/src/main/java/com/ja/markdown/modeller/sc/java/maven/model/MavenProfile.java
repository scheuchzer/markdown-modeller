package com.ja.markdown.modeller.sc.java.maven.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

import com.ja.markdown.modeller.sc.java.model.JavaDependency;

@Data
public class MavenProfile {

	private String id;
	private boolean activeByDefault;
	private Set<JavaDependency> dependencies = new HashSet<>();
	private Set<MavenPlugin> plugins = new HashSet<>();

	public void add(final JavaDependency dependency) {
		dependencies.add(dependency);
	}
}
