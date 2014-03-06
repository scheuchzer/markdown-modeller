package com.ja.markdown.modeller.sc.java.lombok;

import lombok.Data;

import com.ja.markdown.modeller.sc.java.JavaModelPlugin;
import com.ja.markdown.modeller.sc.java.model.JavaAnnotation;
import com.ja.markdown.modeller.sc.java.model.JavaClass;
import com.ja.markdown.modeller.sc.java.model.JavaDependency;
import com.ja.markdown.modeller.sc.java.model.JavaMember;
import com.ja.markdown.modeller.sc.java.model.JavaProject;

public class LombokEnhancer implements JavaModelPlugin {

	@Override
	public void execute(final JavaProject project) {
		project.add(JavaDependency.provided("org.projectlombok:lombok:1.12.4"));
		for (final JavaClass jc : project.getJavaModel().getClasses()) {
			jc.addAnnotation(new JavaAnnotation(new JavaClass(Data.class
					.getName())));
			for (final JavaMember m : jc.getMembers()) {
				m.setWithGetter(false);
				m.setWithSetter(false);
			}
		}
	}
}
