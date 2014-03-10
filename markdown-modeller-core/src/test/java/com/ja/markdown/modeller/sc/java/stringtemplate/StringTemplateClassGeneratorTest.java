package com.ja.markdown.modeller.sc.java.stringtemplate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.ja.markdown.modeller.core.Resources;
import com.ja.markdown.modeller.model.ModelClass;
import com.ja.markdown.modeller.model.ModelClassMember;
import com.ja.markdown.modeller.model.ModelException;
import com.ja.markdown.modeller.sc.java.JdkClass;
import com.ja.markdown.modeller.sc.java.model.JavaAnnotation;
import com.ja.markdown.modeller.sc.java.model.JavaClass;

public class StringTemplateClassGeneratorTest {

	@Test
	public void testGeneratePlainClass() {
		final String expectedCode = Resources.asString(this, "plainClass.txt");
		final JavaClass fooClass = new JavaClass("test.Foo");

		final String actualCode = new StringTemplateGenerator()
				.generateClass(fooClass);
		System.out.println(actualCode);
		assertThat(actualCode, is(expectedCode));
	}

	@Test
	public void testGeneratePlainClassWithAnnotation() {
		final String expectedCode = Resources.asString(this,
				"plainClassWithAnnotation.txt");
		final JavaClass jc = new JavaClass("test.Foo");
		jc.add(new JavaAnnotation(new JavaClass("a.b.MyAnnotation")));

		final String actualCode = new StringTemplateGenerator()
				.generateClass(jc);
		System.out.println(actualCode);
		assertThat(actualCode, is(expectedCode));
	}

	@Test
	public void testGenerateClassWithMemberAnnotation() throws ModelException {
		final String expectedCode = Resources.asString(this,
				"classWithMemberAnnotation.txt");
		final ModelClass mc = new ModelClass("Foo");
		mc.add(ModelClassMember.create("bar: String"));
		final JavaClass jc = new JavaClass("test", mc);
		jc.getMembers().get(0).setClassType(JdkClass.String.instance());
		jc.getMembers().get(0)
				.add(new JavaAnnotation(new JavaClass("a.b.MyAnnotation")));

		final String actualCode = new StringTemplateGenerator()
				.generateClass(jc);
		System.out.println(actualCode);
		assertThat(actualCode, is(expectedCode));
	}

	@Test
	public void testGenerateClassWithStringMember() throws ModelException {
		final String expectedCode = Resources.asString(this,
				"classWithStringMember.txt");
		final ModelClass mc = new ModelClass("Foo");
		mc.add(ModelClassMember.create("bar: String"));
		final JavaClass jc = new JavaClass("test", mc);
		jc.getMembers().get(0).setClassType(JdkClass.String.instance());
		jc.getMembers().get(0).setWithGetter(true);
		jc.getMembers().get(0).setWithSetter(true);
		final String actualCode = new StringTemplateGenerator()
				.generateClass(jc);
		System.out.println(actualCode);
		assertThat(actualCode, is(expectedCode));
	}

	@Test
	public void testGenerateClassWithStringMemberWithImports()
			throws ModelException {
		final String expectedCode = Resources.asString(this,
				"classWithStringMemberWithImports.txt");
		final ModelClass mc = new ModelClass("Foo");
		mc.add(ModelClassMember.create("bar: String"));
		final JavaClass jc = new JavaClass("test", mc);
		jc.getMembers().get(0).setClassType(JdkClass.String.instance());
		jc.getMembers().get(0).setWithGetter(true);
		jc.getMembers().get(0).setWithSetter(true);
		jc.getMembers().get(0).getClassType().setImported(true);
		jc.importClass(JdkClass.String.instance());
		final String actualCode = new StringTemplateGenerator()
				.generateClass(jc);
		System.out.println(actualCode);
		assertThat(actualCode, is(expectedCode));
	}

	@Test
	public void testGenerateClassWithListMember() throws ModelException {
		final String expectedCode = Resources.asString(this,
				"classWithListMember.txt");
		final ModelClass mc = new ModelClass("Foo");
		mc.add(ModelClassMember.create("bar: List[String]"));
		final JavaClass jc = new JavaClass("test", mc);
		jc.getMembers().get(0).setClassType(JdkClass.List.instance());
		jc.getMembers().get(0).setGenericType(JdkClass.String.instance());
		jc.getMembers().get(0).setWithGetter(true);
		jc.getMembers().get(0).setWithSetter(true);
		final String actualCode = new StringTemplateGenerator()
				.generateClass(jc);
		System.out.println(actualCode);
		assertThat(actualCode, is(expectedCode));
	}

	@Test
	public void testGenerateClassWithListMemberWithImports()
			throws ModelException {
		final String expectedCode = Resources.asString(this,
				"classWithListMemberWithImports.txt");
		final ModelClass mc = new ModelClass("Foo");
		mc.add(ModelClassMember.create("bar: List[String]"));
		final JavaClass jc = new JavaClass("test", mc);
		jc.getMembers().get(0).setClassType(JdkClass.List.instance());
		jc.getMembers().get(0).setGenericType(JdkClass.String.instance());
		jc.getMembers().get(0).setWithGetter(true);
		jc.getMembers().get(0).setWithSetter(true);
		jc.getMembers().get(0).getClassType().setImported(true);
		jc.getMembers().get(0).getGenericType().setImported(true);
		jc.importClass(JdkClass.String.instance());
		jc.importClass(JdkClass.List.instance());
		final String actualCode = new StringTemplateGenerator()
				.generateClass(jc);
		System.out.println(actualCode);
		assertThat(actualCode, is(expectedCode));
	}
	//
	// @Test
	// public void testGenerateClassWithListMemberWithImport() {
	// final String expectedCode = Resources.asString(this,
	// "classWithListMemberWithImport.txt");
	// final JavaClass jmc = new JavaClass(new ModelClass("Foo"));
	// jmc.setJavaClassName("my.test.Foo");
	// final JavaMember jmcm = new JavaMember(
	// new ModelClassMember("bar", "List", "String"));
	// jmcm.setJavaType(JdkClass.List.getModelClass());
	// jmcm.setJavaGenericType(JdkClass.String.getModelClass());
	// jmc.importClass(jmcm.getJavaType());
	// jmc.importClass(jmcm.getJavaGenericType());
	// jmc.add(jmcm);
	// final String actualCode = new StringTemplateClassGenerator()
	// .generateClass(jmc);
	// System.out.println(actualCode);
	// assertThat(actualCode, is(expectedCode));
	// }
	//
}
