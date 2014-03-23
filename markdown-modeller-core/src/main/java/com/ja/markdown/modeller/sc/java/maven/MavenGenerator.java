package com.ja.markdown.modeller.sc.java.maven;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;

import com.ja.markdown.modeller.model.ModelException;
import com.ja.markdown.modeller.sc.java.JavaModelPlugin;
import com.ja.markdown.modeller.sc.java.maven.model.MavenPlugin;
import com.ja.markdown.modeller.sc.java.maven.model.MavenProfile;
import com.ja.markdown.modeller.sc.java.maven.model.MavenProject;
import com.ja.markdown.modeller.sc.java.model.JavaProject;
import com.ja.markdown.modeller.sc.java.querydsl.QueryDslModelEnhancer;
import com.ja.markdown.modeller.sc.java.writer.FileSourceCodeWriter;

@Slf4j
public class MavenGenerator implements JavaModelPlugin {

	@Override
	public void execute(final JavaProject project) {

		final MavenProject mavenProject = new MavenProject(project);
		mavenProject.setGroupId(project.getProjectPackage());
		mavenProject.setArtifactId(project.getProject().getAkaName());
		mavenProject.setVersion("0.0.1-SNAPSHOT");
		mavenProject.getJavaProject().add(Dependency.h2.compile());
		mavenProject.getJavaProject().add(Dependency.junit.test());
		mavenProject.getJavaProject().add(Dependency.slf4jApi.compile());

		final List<MavenModelPlugin> plugins = new ArrayList<>();
		plugins.add(new QueryDslModelEnhancer());
		for (final MavenModelPlugin p : plugins) {
			p.execute(mavenProject);
		}

		final MavenPlugin compilerPlugin = Plugin.compiler.instance();
		compilerPlugin.getConfiguration().add("source", "1.7");
		compilerPlugin.getConfiguration().add("target", "1.7");
		mavenProject.add(compilerPlugin);

		if ("war".equals(project.getPackaging())) {
			final MavenPlugin warPlugin = Plugin.war.instance();
			warPlugin.getConfiguration().add("failOnMissingWebXml", "false");
			mavenProject.add(warPlugin);
		}

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
				+ "/template", "UTF-8", '$', '$');
		final ST st = group.getInstanceOf("pom");
		st.add("project", mavenProject);
		final String pomXml = st.render();
		log.debug("Generated code:\n{}", pomXml);

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
		mavenProject.getJavaProject().setWebappOutputFolder(
				new File(projectDir, "src/main/webapp/"));
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
