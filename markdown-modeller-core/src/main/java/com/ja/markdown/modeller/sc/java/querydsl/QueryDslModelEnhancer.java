package com.ja.markdown.modeller.sc.java.querydsl;

import com.ja.markdown.modeller.sc.java.maven.MavenModelPlugin;
import com.ja.markdown.modeller.sc.java.maven.Plugin;
import com.ja.markdown.modeller.sc.java.maven.model.Execution;
import com.ja.markdown.modeller.sc.java.maven.model.MavenPlugin;
import com.ja.markdown.modeller.sc.java.maven.model.MavenProject;
import com.ja.markdown.modeller.sc.java.maven.model.Resource;
import com.ja.markdown.modeller.sc.java.model.JavaDependency;

public class QueryDslModelEnhancer implements MavenModelPlugin {

	private final String outputFolder = "target/generated-sources/querydsl";

	@Override
	public void execute(final MavenProject project) {
		final JavaDependency qjpa = JavaDependency
				.compile("com.mysema.querydsl:querydsl-jpa:3.3.1");
		final JavaDependency qatp = JavaDependency
				.compile("com.mysema.querydsl:querydsl-apt:3.3.1");
		project.getJavaProject().add(qjpa);
		project.getJavaProject().add(qatp);

		final MavenPlugin plugin = Plugin.queryDsl.instance();
		final Execution execution = new Execution();
		execution.addGoal("process");
		execution.getConfiguration().add("outputDirectory", outputFolder);
		execution.getConfiguration().add("processor",
				"com.mysema.query.apt.jpa.JPAAnnotationProcessor");
		plugin.add(execution);
		project.add(plugin);
		project.add(Resource.folder(outputFolder));
	}

}
