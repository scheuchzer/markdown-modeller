package com.ja.markdown.modeller.sc.java.jpa;

import java.util.List;

import com.ja.markdown.modeller.sc.java.JavaModelPlugin;
import com.ja.markdown.modeller.sc.java.JdkClass;
import com.ja.markdown.modeller.sc.java.model.ContentProvider;
import com.ja.markdown.modeller.sc.java.model.JavaAnnotation;
import com.ja.markdown.modeller.sc.java.model.JavaClass;
import com.ja.markdown.modeller.sc.java.model.JavaDependency;
import com.ja.markdown.modeller.sc.java.model.JavaDomainModel;
import com.ja.markdown.modeller.sc.java.model.JavaMember;
import com.ja.markdown.modeller.sc.java.model.JavaMethod;
import com.ja.markdown.modeller.sc.java.model.JavaMethodParameter;
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

		final JavaClass datasourceActivator = new JavaClass(
				project.getProjectPackage() + ".DataSourceActivator");
		datasourceActivator.add(new JavaAnnotation(new JavaClass(
				"javax.ejb.Startup")));
		datasourceActivator.add(new JavaAnnotation(new JavaClass(
				"javax.ejb.Singleton")));
		final JavaAnnotation dsDef = new JavaAnnotation(new JavaClass(
				"javax.annotation.sql.DataSourceDefinition"));
		dsDef.addStringProperty("name", "java:global/jdbc/MyDataSource");
		dsDef.addIntProperty("minPoolSize", 0);
		dsDef.addIntProperty("initialPoolSize", 0);
		dsDef.addStringProperty("className", "org.h2.Driver");
		dsDef.addStringProperty("user", "sa");
		dsDef.addStringProperty("password", "");
		dsDef.addStringProperty("url", "jdbc:h2:mem:test");
		datasourceActivator.add(dsDef);

		final JavaMember em = new JavaMember("em");
		em.setClassType(JpaClass.EntityManager.instance());
		em.add(new JavaAnnotation(JpaClass.PersistenceContext.instance()));
		datasourceActivator.add(em);

		final JavaMethod init = new JavaMethod("init");
		init.add(new JavaAnnotation(new JavaClass(
				"javax.annotation.PostConstruct")));
		init.setContentProvider(new ContentProvider() {

			@Override
			public String getContent() {
				final StringBuilder code = new StringBuilder();
				for (final JavaClass jc : project.getJavaModel().getClasses(
						"entity")) {
					if (project.getProject().getDomainModel()
							.getMasterDataClasses().contains(jc.getModel())) {
						code.append("initDb(")
								.append(jc.getSourceCodeTypeName())
								.append(".class);\n");
					}
				}
				return code.toString();
			}
		});
		datasourceActivator.add(init);

		final JavaMethod initDb = new JavaMethod("initDb");
		final JavaClass clazz = JdkClass.Class.instance();
		final JavaClass methodClass = JdkClass.Method.instance();
		initDb.add(new JavaMethodParameter("type", clazz));
		initDb.addUsedClass(methodClass);
		initDb.setContentProvider(new ContentProvider() {

			@Override
			public String getContent() {
				return "if (!isEmpty(type)) {\n"
						+ "	return;\n"
						+ "}\n"
						+ "try {\n"
						+ "	final "
						+ methodClass.getSourceCodeTypeName()
						+ " m = type.getMethod(\"setName\", String.class);\n"
						+ "	for (int i = 1; i <= 2; i++) {\n"
						+ "		final Object o = type.newInstance();\n"
						+ "		m.invoke(o, String.format(\"%s-%s\", type.getSimpleName(), i));\n"
						+ "		em.persist(o);\n" + "	}\n"
						+ "} catch (final Exception e) {\n"
						+ "	e.printStackTrace();\n" + "}";
			}
		});

		datasourceActivator.add(initDb);

		final JavaMethod isEmpty = new JavaMethod("isEmpty");
		isEmpty.setReturnType(JdkClass.Boolean.instance());
		isEmpty.add(new JavaMethodParameter("type", clazz));
		final String code = "return em\n"
				+ "		.createQuery(\n"
				+ "              String.format(\"select o from %s o\",\n"
				+ "              		type.getSimpleName()), type).setMaxResults(1)\n"
				+ "      .getResultList().isEmpty();";
		isEmpty.setContent(code);
		datasourceActivator.add(isEmpty);
		project.getJavaModel().add(datasourceActivator);
	}

	private Resource createPersistenceXml() {
		// TODO: What about using the PersistenceXmlDescriptor from Shrinkwrap
		// for code generation?
		final PersistenceXml persistenceXml = new PersistenceXml();
		persistenceXml.setUnitName("unit");
		persistenceXml.setTransactionType("JTA");
		persistenceXml.setJtaDataSource("java:global/jdbc/MyDataSource");
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
