package com.ja.markdown.modeller.sc.java.maven.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ja.markdown.modeller.sc.java.model.JavaBase;
import com.ja.markdown.modeller.sc.java.model.JavaProject;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class MavenProject extends JavaBase {

	private final JavaProject javaProject;

	private String groupId;
	private String artifactId;
	private String version;
	private String packaging;
	private String name;

	private Set<MavenPlugin> plugins = new HashSet<>();

	private Set<MavenProfile> profiles = new HashSet<>();

	public MavenProject(final JavaProject javaProject) {
		this.javaProject = javaProject;
	}

	public void add(final MavenProfile profile) {
		profiles.add(profile);
	}

	public void add(final MavenPlugin plugin) {
		plugins.add(plugin);
	}

}
