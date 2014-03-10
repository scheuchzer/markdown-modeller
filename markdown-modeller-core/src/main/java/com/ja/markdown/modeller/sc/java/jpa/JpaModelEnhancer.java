package com.ja.markdown.modeller.sc.java.jpa;

import java.util.List;

import com.ja.markdown.modeller.sc.java.JavaModelPlugin;
import com.ja.markdown.modeller.sc.java.JdkClass;
import com.ja.markdown.modeller.sc.java.model.JavaAnnotation;
import com.ja.markdown.modeller.sc.java.model.JavaClass;
import com.ja.markdown.modeller.sc.java.model.JavaDependency;
import com.ja.markdown.modeller.sc.java.model.JavaDomainModel;
import com.ja.markdown.modeller.sc.java.model.JavaMember;
import com.ja.markdown.modeller.sc.java.model.JavaModel;
import com.ja.markdown.modeller.sc.java.model.JavaProject;
import com.ja.markdown.modeller.sc.java.model.PersistenceXml;
import com.ja.markdown.modeller.sc.java.model.Resource;

public class JpaModelEnhancer implements JavaModelPlugin {

	@Override
	public void execute(final JavaProject project) {
		project.add(JavaDependency.test("org.hamcrest:hamcrest-all:1.3"));

		final JavaDomainModel pojoModel = project.getJavaModel()
				.cloneDomainModel("entity");

		enhanceMasterData(project.getJavaModel(),
				pojoModel.getMasterDataClasses());
		enhance(project.getJavaModel(), pojoModel.getBusinessDataClasses());

		project.addMetaInfResource(createPersistenceXml());
		project.addTestMetaInfResource(createTestPersistenceXml(project));
	}

	private Resource createPersistenceXml() {
		final PersistenceXml persistenceXml = new PersistenceXml();
		persistenceXml.setUnitName("unit");
		persistenceXml.setTransactionType("JTA");
		persistenceXml.setJtaDataSource("java:comp/DefaultDataSource");
		return persistenceXml;
	}

	private Resource createTestPersistenceXml(final JavaProject project) {
		final PersistenceXml persistenceXml = new PersistenceXml();
		persistenceXml.setUnitName("unit");
		persistenceXml.setTransactionType("RESOURCE_LOCAL");
		for (final JavaClass jc : project.getJavaModel().getClasses()) {
			if (jc.getName().contains(".entity.")) {
				persistenceXml.add(jc);
			}
		}
		final JpaProperties props = new JpaProperties(persistenceXml);
		props.setDriver("org.h2.Driver");
		props.setUrl("jdbc:h2:file:test.db");
		props.setUser("sa");
		props.setPassword("");
		return persistenceXml;
	}

	private void enhanceMasterData(final JavaModel javaModel,
			final List<JavaClass> classes) {
		for (final JavaClass jc : classes) {
			enhance(jc);
			final JpaMasterDataTestClass test = new JpaMasterDataTestClass(jc);
			javaModel.addTest(test);
			javaModel.add(jc);
		}

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
