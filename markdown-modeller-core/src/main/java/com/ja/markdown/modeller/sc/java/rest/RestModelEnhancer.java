package com.ja.markdown.modeller.sc.java.rest;

import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import com.ja.markdown.modeller.sc.java.JavaModelPlugin;
import com.ja.markdown.modeller.sc.java.JdkClass;
import com.ja.markdown.modeller.sc.java.jpa.JpaClass;
import com.ja.markdown.modeller.sc.java.maven.Dependency;
import com.ja.markdown.modeller.sc.java.model.BeansXml;
import com.ja.markdown.modeller.sc.java.model.JavaAnnotation;
import com.ja.markdown.modeller.sc.java.model.JavaClass;
import com.ja.markdown.modeller.sc.java.model.JavaMember;
import com.ja.markdown.modeller.sc.java.model.JavaMethod;
import com.ja.markdown.modeller.sc.java.model.JavaProject;

@Slf4j
public class RestModelEnhancer implements JavaModelPlugin {

	@Override
	public void execute(final JavaProject project) {
		project.setPackaging("war");
		project.addWebappResource(BeansXml.createWebInf());
		project.add(Dependency.javaeeApi.provided());

		final JavaClass app = new JavaClass(
				project.getProjectPackage("boundary.rest") + ".RestApplication",
				new JavaClass("javax.ws.rs.core.Application"));

		final JavaAnnotation annotation = new JavaAnnotation(new JavaClass(
				"javax.ws.rs.ApplicationPath"));
		annotation.setValue("/rest");
		app.add(annotation);
		project.getJavaModel().add(app);

		createRestResources(project.getJavaModel().getClasses("entity"),
				project);
		// restify(project.getJavaModel().getClasses("entity"));
	}

	private void createRestResources(final Set<JavaClass> classes,
			final JavaProject project) {
		for (final JavaClass jc : classes) {
			createRestResource(jc, project);
		}

	}

	private void createRestResource(final JavaClass jc,
			final JavaProject project) {
		String name = jc.getName() + "Resource";
		name = name.replace("entity", "boundary.rest");
		final JavaClass resource = new JavaClass(name);
		restify(resource, jc);
		project.getJavaModel().add(resource);

	}

	/*
	 * private void restify(final Set<JavaClass> classes) { for (final JavaClass
	 * jc : classes) { restify(jc); }
	 * 
	 * }
	 */

	private void restify(final JavaClass jc, final JavaClass entity) {
		log.info("Restify class {}", jc.getName());
		jc.add(new JavaAnnotation(new JavaClass("javax.ejb.Stateless")));
		final JavaAnnotation path = new JavaAnnotation(new JavaClass(
				"javax.ws.rs.Path"));
		path.setValue("/" + entity.getCamelCaseSimpleName());
		jc.add(path);

		addListService(jc, entity);
		for (final JavaMember jm : jc.getMembers()) {
			// restify(jm, jc);
		}
	}

	private void addListService(final JavaClass jc, final JavaClass entity) {
		final JavaMethod method = new JavaMethod("get");
		final JavaClass returnType = JdkClass.List.instance();

		returnType.setGenericType(new JavaClass(entity.getName()));
		method.setReturnType(returnType);
		final JavaAnnotation path = new JavaAnnotation(new JavaClass(
				"javax.ws.rs.Path"));
		path.setValue("/");
		method.add(path);
		method.add(new JavaAnnotation(new JavaClass("javax.ws.rs.GET")));
		method.setContent(String
				.format("return em.createQuery(\"select o from %s o\", %s.class).getResultList();",
						entity.getSimpleName(), entity.getSimpleName()));
		jc.add(method);

		final JavaMember dao = new JavaMember("em");
		dao.setClassType(JpaClass.EntityManager.instance());
		dao.setWithGetter(false);
		dao.setWithSetter(false);
		dao.add(new JavaAnnotation(JpaClass.PersistenceContext.instance()));
		jc.add(dao);

	}
}
