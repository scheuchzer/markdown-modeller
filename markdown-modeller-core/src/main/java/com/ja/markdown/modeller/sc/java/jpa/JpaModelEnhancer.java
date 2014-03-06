package com.ja.markdown.modeller.sc.java.jpa;

import java.util.List;

import com.ja.markdown.modeller.sc.java.JavaModelPlugin;
import com.ja.markdown.modeller.sc.java.JdkClass;
import com.ja.markdown.modeller.sc.java.model.JavaAnnotation;
import com.ja.markdown.modeller.sc.java.model.JavaClass;
import com.ja.markdown.modeller.sc.java.model.JavaDomainModel;
import com.ja.markdown.modeller.sc.java.model.JavaMember;
import com.ja.markdown.modeller.sc.java.model.JavaModel;
import com.ja.markdown.modeller.sc.java.model.JavaProject;

public class JpaModelEnhancer implements JavaModelPlugin {

	@Override
	public void execute(final JavaProject project) {
		// project.add(JavaDependency.provided(dependency));
		final JavaDomainModel pojoModel = project.getJavaModel()
				.cloneDomainModel("entity");

		enhance(project.getJavaModel(), pojoModel.getMasterDataClasses());
		enhance(project.getJavaModel(), pojoModel.getBusinessDataClasses());
	}

	private void enhance(final JavaModel javaModel,
			final List<JavaClass> classes) {
		for (final JavaClass jc : classes) {
			enhance(jc);
			javaModel.add(jc);
		}

	}

	private void enhance(final JavaClass jc) {
		jc.add(new JavaAnnotation(JpaClass.Entity.instance()));
		for (final JavaMember m : jc.getMembers()) {
			enhanceMember(m, jc);
		}
	}

	private void enhanceMember(final JavaMember m, final JavaClass owner) {
		m.setWithGetter(true);
		m.setWithSetter(true);

		if ("id".equalsIgnoreCase(m.getName())) {
			m.add(new JavaAnnotation(JpaClass.Id.instance()));
			m.add(new JavaAnnotation(JpaClass.GeneratedValue.instance()));
		}

		if (JdkClass.List.instance().equals(m.getClassType())) {
			m.add(new JavaAnnotation(JpaClass.ManyToMany.instance()));
		}

		if (owner.getPackage().equals(m.getClassType().getPackage())) {
			m.add(new JavaAnnotation(JpaClass.ManyToOne.instance()));
		}

	}
}
