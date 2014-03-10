package com.ja.markdown.modeller.sc.java.model;

import lombok.Data;

import org.apache.commons.lang3.StringUtils;

@Data
public class JavaDependency {
	public enum Scope {
		compile, test, provided, runtime
	};

	private String groupId;
	private String artifactId;
	private String version;
	private Scope scope;

	public static JavaDependency compile(final String dependency) {
		return dependency(Scope.compile, dependency);
	}

	public static JavaDependency test(final String dependency) {
		return dependency(Scope.test, dependency);
	}

	public static JavaDependency provided(final String dependency) {
		return dependency(Scope.provided, dependency);
	}

	public static JavaDependency runtime(final String dependency) {
		return dependency(Scope.runtime, dependency);
	}

	private static JavaDependency dependency(final Scope scope,
			final String dependency) {
		final String[] tokens = StringUtils.split(dependency, ":");
		if (tokens.length < 3) {
			throw new RuntimeException("Invalid dependency definition. "
					+ dependency);
		}
		final JavaDependency dep = new JavaDependency();
		dep.setGroupId(tokens[0]);
		dep.setArtifactId(tokens[1]);
		dep.setVersion(tokens[2]);
		if (!Scope.compile.equals(scope)) {
			dep.setScope(scope);
		}
		return dep;
	}
}
