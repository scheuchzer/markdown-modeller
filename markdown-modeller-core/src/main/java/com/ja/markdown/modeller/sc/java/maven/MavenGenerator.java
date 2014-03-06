package com.ja.markdown.modeller.sc.java.maven;

import com.ja.markdown.modeller.sc.java.JavaModelPlugin;
import com.ja.markdown.modeller.sc.java.model.JavaProject;

public class MavenGenerator implements JavaModelPlugin {

	@Override
	public void execute(final JavaProject project) {

		System.out.println(project.getOutputFolder().getAbsolutePath());
	}

}
