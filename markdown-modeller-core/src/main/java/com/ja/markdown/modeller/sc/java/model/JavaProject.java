package com.ja.markdown.modeller.sc.java.model;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;

import com.ja.markdown.modeller.model.Project;

@Data
@Slf4j
public class JavaProject {

	private final Project project;

	private final JavaModel javaModel;

	private Set<JavaDependency> dependencies = new HashSet<>();

	private Set<Resource> metaInfResources = new HashSet<>();
	private Set<Resource> testMetaInfResources = new HashSet<>();

	private File classesOutputFolder;
	private File testClassesOutputFolder;
	private File resourcesOutputFolder;
	private File testResourcesOutputFolder;

	public JavaProject(final Project project) {
		this.project = project;
		this.javaModel = new JavaModel(this);
	}

	public String getProjectPackage() {
		final StringBuilder buf = new StringBuilder();
		buf.append(project.getGroupId()).append('.');
		String projectName = StringUtils.trimToNull(project.getAkaName());
		if (StringUtils.isEmpty(projectName)) {
			projectName = project.getTitle().toLowerCase();
		}
		projectName = StringUtils.replaceChars(projectName,
				" ,;.:-_+\"*ç%&/()=?@#|¬|¢{}[]()~^`", "");
		buf.append(projectName);
		final String pkg = buf.toString();
		return pkg;
	}

	public String getProjectPackage(final String module) {
		return getProjectPackage() + "." + module;
	}

	public void add(final JavaDependency dependency) {
		dependencies.add(dependency);

	}

	public void addMetaInfResource(final Resource resource) {
		metaInfResources.add(resource);
	}

	public void addTestMetaInfResource(final Resource resource) {
		testMetaInfResources.add(resource);
	}

	public <T> T getMetaInfResource(final String name) {
		return getResource(name, getMetaInfResources());
	}

	public <T> T getTestMetaInfResource(final String name) {
		return getResource(name, getTestMetaInfResources());
	}

	@SuppressWarnings("unchecked")
	private <T> T getResource(final String name, final Set<Resource> resources) {
		Resource resource = null;
		for (final Resource r : resources) {
			if (name.equals(r.getName())) {
				resource = r;
				break;
			}
		}
		if (resource == null) {
			log.info("{} not found in resources", name);
		}
		return (T) resource;
	}
}
