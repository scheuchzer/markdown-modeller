package com.ja.markdown.modeller.sc.java;

import java.util.Arrays;
import java.util.List;

import com.ja.markdown.modeller.model.Project;
import com.ja.markdown.modeller.sc.java.jpa.JpaModelEnhancer;
import com.ja.markdown.modeller.sc.java.lombok.LombokEnhancer;
import com.ja.markdown.modeller.sc.java.maven.MavenGenerator;
import com.ja.markdown.modeller.sc.java.model.JavaProject;
import com.ja.markdown.modeller.sc.java.pojo.PojoModelEnhancer;
import com.ja.markdown.modeller.sc.java.stringtemplate.StringTemplateClassGenerator;

public class JavaSourceCode {

	public void execute(final Project project) {
		final JavaProject javaProject = new JavaProject(project);

		// TODO: load through spi.
		final List<? extends JavaModelPlugin> plugins = Arrays.asList(
				new PojoModelEnhancer(), new JpaModelEnhancer(),
				new LombokEnhancer(), new ImportsEnhancer(),
				new StringTemplateClassGenerator(),
				// new FileSourceCodeWriter(),
				new MavenGenerator());

		for (final JavaModelPlugin plugin : plugins) {
			plugin.execute(javaProject);
		}
	}
}
