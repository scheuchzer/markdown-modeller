package com.ja.markdown.modeller.sc.java.maven;

import com.ja.markdown.modeller.sc.java.model.JavaDependency;

public enum Dependency {

	h2("com.h2database:h2:1.3.160"), hibernateJpa21Api(
			"org.hibernate.javax.persistence:hibernate-jpa-2.1-api:1.0.0.Final"), hibernateEntityManager(
			"org.hibernate:hibernate-entitymanager:4.3.4.Final"), junit(
			"junit:junit:4.11"), eclipseLink(
			"org.eclipse.persistence: org.eclipse.persistence.jpa:2.5.1"), javaeeApi(
			"javax:javaee-api:7.0"), slf4jApi("org.slf4j:slf4j-api:1.7.6");

	private String dependency;

	private Dependency(final String dependency) {
		this.dependency = dependency;
	}

	public JavaDependency provided() {
		return JavaDependency.provided(dependency);
	}

	public JavaDependency runtime() {
		return JavaDependency.runtime(dependency);
	}

	public JavaDependency test() {
		return JavaDependency.test(dependency);
	}

	public JavaDependency compile() {
		return JavaDependency.compile(dependency);
	}
}
