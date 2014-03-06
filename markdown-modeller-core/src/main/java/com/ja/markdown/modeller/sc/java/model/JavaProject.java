package com.ja.markdown.modeller.sc.java.model;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

import org.apache.commons.lang3.StringUtils;

import com.ja.markdown.modeller.model.Project;

@Data
public class JavaProject {

	private final Project project;

	private final JavaModel javaModel;

	private final File outputFolder;

	private Set<JavaDependency> dependencies = new HashSet<>();

	public JavaProject(final Project project) {
		this.project = project;
		this.javaModel = new JavaModel(this);
		this.outputFolder = new File(".");
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
}
