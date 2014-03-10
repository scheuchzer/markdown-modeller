package com.ja.markdown.modeller.sc.java.maven;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;

import com.ja.markdown.modeller.model.ModelException;
import com.ja.markdown.modeller.sc.java.JavaModelPlugin;
import com.ja.markdown.modeller.sc.java.maven.model.MavenProfile;
import com.ja.markdown.modeller.sc.java.maven.model.MavenProject;
import com.ja.markdown.modeller.sc.java.model.JavaProject;
import com.ja.markdown.modeller.sc.java.writer.FileSourceCodeWriter;

@Slf4j
public class MavenGenerator implements JavaModelPlugin {

	@Override
	public void execute(final JavaProject project) {

		final MavenProject mavenProject = new MavenProject(project);
		mavenProject.setGroupId(project.getProjectPackage());
		mavenProject.setArtifactId(project.getProject().getAkaName());
		mavenProject.setVersion("0.0.1-SNAPSHOT");
		mavenProject.getJavaProject().add(Dependency.h2.test());
		mavenProject.getJavaProject().add(Dependency.junit.test());

		final MavenProfile profileHibernate = new MavenProfile();
		profileHibernate.setId("hiberate");
		profileHibernate.setActiveByDefault(true);
		profileHibernate.add(Dependency.hibernateJpa21Api.provided());
		profileHibernate.add(Dependency.hibernateEntityManager.provided());
		mavenProject.add(profileHibernate);

		final MavenProfile profileEclipseLink = new MavenProfile();
		profileEclipseLink.setId("eclipseLink");
		profileEclipseLink.setActiveByDefault(false);
		profileEclipseLink.add(Dependency.eclipseLink.provided());
		mavenProject.add(profileEclipseLink);

		log.info("Creating: pom.xml");
		final STGroup group = new STGroupDir(getClass().getPackage().getName()
				.replace(".", "/")
				+ "/template");
		final ST st = group.getInstanceOf("pom");
		st.add("project", mavenProject);
		final String pomXml = st.render();
		log.info("Generated code:\n{}", pomXml);

		final File projectDir = new File(mavenProject.getJavaProject()
				.getProject().getOutputFolder(), mavenProject.getArtifactId());
		log.info("Project dir is: {}", projectDir.getAbsolutePath());
		final File pomFile = new File(projectDir, "pom.xml");
		mavenProject.getJavaProject().setClassesOutputFolder(
				new File(projectDir, "src/main/java"));
		mavenProject.getJavaProject().setTestClassesOutputFolder(
				new File(projectDir, "src/test/java"));
		mavenProject.getJavaProject().setResourcesOutputFolder(
				new File(projectDir, "src/main/resources"));
		mavenProject.getJavaProject().setTestResourcesOutputFolder(
				new File(projectDir, "src/test/resources"));
		new FileSourceCodeWriter().execute(mavenProject.getJavaProject());

		log.info("Writing: {}", pomFile.getAbsolutePath());
		try (OutputStream os = new FileOutputStream(pomFile)) {
			IOUtils.write(pomXml, os);
		} catch (final Exception e) {
			mavenProject.add(new ModelException("Couldn't write pom file "
					+ pomFile.getAbsolutePath(), e));
			e.printStackTrace();
		}
	}
}
