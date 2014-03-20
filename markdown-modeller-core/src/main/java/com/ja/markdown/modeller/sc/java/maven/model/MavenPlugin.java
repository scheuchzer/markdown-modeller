package com.ja.markdown.modeller.sc.java.maven.model;

import lombok.Data;

import org.apache.commons.lang3.StringUtils;

@Data
public class MavenPlugin {

	private String groupId;
	private String artifactId;
	private String version;

	private PluginConfiguratoin configuration = new PluginConfiguratoin();

	public static MavenPlugin create(final String definition) {
		final String[] tokens = StringUtils.split(definition, ":");
		if (tokens.length < 3) {
			throw new RuntimeException("Invalid dependency definition. "
					+ definition);
		}
		final MavenPlugin plugin = new MavenPlugin();
		plugin.setGroupId(tokens[0]);
		plugin.setArtifactId(tokens[1]);
		plugin.setVersion(tokens[2]);
		return plugin;
	}
}
