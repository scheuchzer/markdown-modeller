package com.ja.markdown.modeller.sc.java.pojo;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ja.markdown.modeller.model.ModelClass;
import com.ja.markdown.modeller.model.ModelClassMember;
import com.ja.markdown.modeller.model.ModelException;
import com.ja.markdown.modeller.model.Project;
import com.ja.markdown.modeller.sc.java.JavaClassFactory;
import com.ja.markdown.modeller.sc.java.model.JavaClass;
import com.ja.markdown.modeller.sc.java.model.JavaMember;
import com.ja.markdown.modeller.sc.java.model.JavaProject;

public class PojoModelEnhancerTest {

	@Test
	public void enhanceClassWithMember() throws ModelException {
		final Project project = new Project();
		project.setTitle("test");
		project.setGroupId("com.ja");
		final ModelClass mc = ModelClass.create("Foo");
		mc.add(ModelClassMember.create("foo: String"));
		project.getDomainModel().addMasterData(mc);

		final JavaProject javaProject = new JavaProject(project);
		new PojoModelEnhancer().execute(javaProject);

		final List<JavaClass> classes = new ArrayList<>(javaProject
				.getJavaModel().getClasses());
		assertThat(classes.size(), is(1));
		final JavaClass actualClass = classes.get(0);
		assertThat(actualClass.getName(), is("com.ja.test.pojo.Foo"));
		assertThat(actualClass.getMembers().size(), is(1));
		final JavaMember actualMember = actualClass.getMembers().get(0);
		final JavaClassFactory jcf = new JavaClassFactory("com.ja.test.pojo");
		assertThat(actualMember.getName(), is("foo"));
		assertThat(actualMember.getClassType(), is(jcf.resolve("String")));

	}

	@Test
	public void enhanceClassWithGenericMember() throws ModelException {
		final Project project = new Project();
		project.setTitle("test");
		project.setGroupId("com.ja");
		final ModelClass mc = ModelClass.create("Foo");
		mc.add(ModelClassMember.create("foo: List[String]"));
		project.getDomainModel().addMasterData(mc);

		final JavaProject javaProject = new JavaProject(project);
		new PojoModelEnhancer().execute(javaProject);

		final List<JavaClass> classes = new ArrayList<>(javaProject
				.getJavaModel().getClasses());
		assertThat(classes.size(), is(1));
		final JavaClass actualClass = classes.get(0);
		assertThat(actualClass.getName(), is("com.ja.test.pojo.Foo"));
		assertThat(actualClass.getMembers().size(), is(1));
		final JavaMember actualMember = actualClass.getMembers().get(0);
		final JavaClassFactory jcf = new JavaClassFactory("com.ja.test.pojo");
		assertThat(actualMember.getName(), is("foo"));
		assertThat(actualMember.getClassType(), is(jcf.resolve("List")));
		assertThat(actualMember.getGenericType(), is(jcf.resolve("String")));
		assertThat(actualMember.isWithGetter(), is(true));
		assertThat(actualMember.isWithSetter(), is(true));

	}
}
