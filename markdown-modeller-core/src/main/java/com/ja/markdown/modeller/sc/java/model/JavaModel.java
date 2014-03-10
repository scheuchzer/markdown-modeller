package com.ja.markdown.modeller.sc.java.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class JavaModel {
	// TODO: Merge with JavaProject!
	private final JavaProject project;

	private final Set<JavaClass> classes = new HashSet<>();
	private final Set<JavaClass> testClasses = new HashSet<>();

	public JavaModel(final JavaProject project) {
		this.project = project;
	}

	public JavaDomainModel cloneDomainModel(final String module) {
		return new JavaDomainModelCloner(project.getProjectPackage(module))
				.clone(project.getProjectPackage(module), project.getProject()
						.getDomainModel(), project.getProjectPackage(module));
	}

	public void add(final JavaClass jc) {
		if (classes.contains(jc)) {
			throw new IllegalArgumentException("Class already registered. "
					+ jc.getName());
		}
		classes.add(jc);
	}

	public void addTest(final JavaClass jc) {
		if (testClasses.contains(jc)) {
			throw new IllegalArgumentException("Class already registered. "
					+ jc.getName());
		}
		testClasses.add(jc);
	}
}
