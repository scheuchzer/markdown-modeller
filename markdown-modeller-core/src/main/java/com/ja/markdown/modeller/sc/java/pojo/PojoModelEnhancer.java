package com.ja.markdown.modeller.sc.java.pojo;

import java.util.List;

import com.ja.markdown.modeller.sc.java.JavaModelPlugin;
import com.ja.markdown.modeller.sc.java.model.JavaClass;
import com.ja.markdown.modeller.sc.java.model.JavaDomainModel;
import com.ja.markdown.modeller.sc.java.model.JavaMember;
import com.ja.markdown.modeller.sc.java.model.JavaModel;
import com.ja.markdown.modeller.sc.java.model.JavaProject;

public class PojoModelEnhancer implements JavaModelPlugin {

	@Override
	public void execute(final JavaProject project) {
		final JavaDomainModel pojoModel = project.getJavaModel()
				.cloneDomainModel("pojo");

		enhance(project.getJavaModel(), pojoModel.getMasterDataClasses());
		enhance(project.getJavaModel(), pojoModel.getBusinessDataClasses());
	}

	private void enhance(final JavaModel javaModel,
			final List<JavaClass> classes) {
		for (final JavaClass jc : classes) {
			enhanceMembers(jc.getMembers());
			javaModel.add(jc);
		}

	}

	private void enhanceMembers(final List<JavaMember> members) {
		for (final JavaMember m : members) {
			m.setWithGetter(true);
			m.setWithSetter(true);
		}

	}
}
