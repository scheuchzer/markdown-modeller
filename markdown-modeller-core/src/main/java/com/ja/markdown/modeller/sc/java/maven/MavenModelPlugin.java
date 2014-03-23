package com.ja.markdown.modeller.sc.java.maven;

import com.ja.markdown.modeller.sc.java.maven.model.MavenProject;

public interface MavenModelPlugin {

	void execute(final MavenProject project);
}
