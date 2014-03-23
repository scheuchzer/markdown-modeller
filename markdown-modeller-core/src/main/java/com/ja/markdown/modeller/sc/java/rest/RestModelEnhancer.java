package com.ja.markdown.modeller.sc.java.rest;

import static com.ja.markdown.modeller.sc.java.ejb.EjbClass.Stateless;
import static com.ja.markdown.modeller.sc.java.rest.JaxRsClass.Application;
import static com.ja.markdown.modeller.sc.java.rest.JaxRsClass.ApplicationPath;
import static com.ja.markdown.modeller.sc.java.rest.JaxRsClass.GET;
import static com.ja.markdown.modeller.sc.java.rest.JaxRsClass.Path;
import static com.ja.markdown.modeller.sc.java.rest.JaxRsClass.PathParam;

import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import com.ja.markdown.modeller.sc.java.JavaModelPlugin;
import com.ja.markdown.modeller.sc.java.JdkClass;
import com.ja.markdown.modeller.sc.java.jpa.JpaClass;
import com.ja.markdown.modeller.sc.java.maven.Dependency;
import com.ja.markdown.modeller.sc.java.model.BeansXml;
import com.ja.markdown.modeller.sc.java.model.ContentProvider;
import com.ja.markdown.modeller.sc.java.model.JavaAnnotation;
import com.ja.markdown.modeller.sc.java.model.JavaClass;
import com.ja.markdown.modeller.sc.java.model.JavaMember;
import com.ja.markdown.modeller.sc.java.model.JavaMethod;
import com.ja.markdown.modeller.sc.java.model.JavaMethodParameter;
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
				Application.instance());

		final JavaAnnotation annotation = new JavaAnnotation(
				ApplicationPath.instance());
		annotation.setValue("/rest");
		app.add(annotation);
		project.getJavaModel().add(app);

		createRestResources(project.getJavaModel().getClasses("entity"),
				project);
		restifyEntities(project.getJavaModel().getClasses("entity"));
	}

	private void restifyEntities(final Set<JavaClass> entities) {
		for (final JavaClass jc : entities) {
			restifyEntity(jc);
		}

	}

	private void restifyEntity(final JavaClass entity) {
		final JavaMethod get = new JavaMethod("get");
		get.setReturnType(new JavaClass(entity));
		get.add(new JavaAnnotation(GET.instance()));
		get.setContent("return this;");
		entity.add(get);

		for (final JavaMember m : entity.getMembers()) {
			final JavaMethod g = new JavaMethod(String.format("get%s",
					m.getNameForMethod()));
			g.setReturnType(new JavaClass(m.getClassType()));
			g.add(new JavaAnnotation(GET.instance()));
			final JavaAnnotation path = new JavaAnnotation(Path.instance());
			path.setValue("/" + m.getName());
			g.add(path);
			g.setContent(String.format("return %s;", m.getName()));
			entity.add(g);
		}

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
		restifyResourceClass(resource, jc);
		project.getJavaModel().add(resource);

	}

	private void restifyResourceClass(final JavaClass jc, final JavaClass entity) {
		log.info("Restify class {}", jc.getName());
		jc.add(new JavaAnnotation(Stateless.instance()));
		final JavaAnnotation path = new JavaAnnotation(Path.instance());
		path.setValue("/" + entity.getCamelCaseSimpleName());
		jc.add(path);

		addListService(jc, entity);
		addGetByIdService(jc, entity);
	}

	private void addGetByIdService(final JavaClass jc, final JavaClass entity) {
		final JavaMethod method = new JavaMethod("getById");
		method.setReturnType(entity);
		final JavaAnnotation path = new JavaAnnotation(Path.instance());
		path.setValue("/{id}");
		method.add(path);

		final JavaMethodParameter idParam = new JavaMethodParameter("id",
				JdkClass.Long.instance());
		final JavaAnnotation pathParam = new JavaAnnotation(
				PathParam.instance());
		pathParam.setValue("id");
		idParam.add(pathParam);
		method.add(idParam);
		jc.addUsedClass(entity);
		method.setContentProvider(new ContentProvider() {

			@Override
			public String getContent() {
				return String.format("return em.find(%s.class, id);",
						entity.getSourceCodeTypeName());
			}
		});

		jc.add(method);
	}

	private void addListService(final JavaClass jc, final JavaClass entity) {
		final JavaMethod method = new JavaMethod("get");
		final JavaClass returnType = JdkClass.List.instance();

		returnType.setGenericType(entity);
		method.setReturnType(returnType);
		method.add(new JavaAnnotation(GET.instance()));
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
