package com.ja.markdown.modeller.generator.java.stringtemplate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.ja.markdown.modeller.core.Resources;
import com.ja.markdown.modeller.generator.java.JavaModelClass;
import com.ja.markdown.modeller.generator.java.JavaModelClassMember;
import com.ja.markdown.modeller.generator.java.JdkClass;
import com.ja.markdown.modeller.model.ModelClass;
import com.ja.markdown.modeller.model.ModelClassMember;

public class StringTemplateClassGeneratorTest {

	@Test
	public void testGeneratePlainClass() {
		final String expectedCode = Resources.asString(this, "plainClass.txt");
		final JavaModelClass jmc = new JavaModelClass(new ModelClass("Foo"));
		jmc.setJavaClassName("my.test.Foo");
		final String actualCode = new StringTemplateClassGenerator()
				.generateClass(jmc);
		System.out.println(actualCode);
		assertThat(actualCode, is(expectedCode));
	}

	@Test
	public void testGenerateClassWithStringMember() {
		final String expectedCode = Resources.asString(this,
				"classWithStringMember.txt");
		final JavaModelClass jmc = new JavaModelClass(new ModelClass("Foo"));
		jmc.setJavaClassName("my.test.Foo");
		final JavaModelClassMember jmcm = new JavaModelClassMember(
				new ModelClassMember("bar", "String", null));
		jmcm.setJavaType(JdkClass.String.getModelClass());
		jmc.add(jmcm);
		final String actualCode = new StringTemplateClassGenerator()
				.generateClass(jmc);
		System.out.println(actualCode);
		assertThat(actualCode, is(expectedCode));
	}

	@Test
	public void testGenerateClassWithStringMemberWithImports() {
		final String expectedCode = Resources.asString(this,
				"classWithStringMemberWithImports.txt");
		final JavaModelClass jmc = new JavaModelClass(new ModelClass("Foo"));
		jmc.setJavaClassName("my.test.Foo");
		final JavaModelClassMember jmcm = new JavaModelClassMember(
				new ModelClassMember("bar", "String", null));
		jmcm.setJavaType(JdkClass.String.getModelClass());
		jmc.importClass(jmcm.getJavaType());
		jmc.add(jmcm);
		final String actualCode = new StringTemplateClassGenerator()
				.generateClass(jmc);
		System.out.println(actualCode);
		assertThat(actualCode, is(expectedCode));
	}

	@Test
	public void testGenerateClassWithListMember() {
		final String expectedCode = Resources.asString(this,
				"classWithListMember.txt");
		final JavaModelClass jmc = new JavaModelClass(new ModelClass("Foo"));
		jmc.setJavaClassName("my.test.Foo");
		final JavaModelClassMember jmcm = new JavaModelClassMember(
				new ModelClassMember("bar", "List", "String"));
		jmcm.setJavaType(JdkClass.List.getModelClass());
		jmcm.setJavaGenericType(JdkClass.String.getModelClass());
		jmc.add(jmcm);
		final String actualCode = new StringTemplateClassGenerator()
				.generateClass(jmc);
		System.out.println(actualCode);
		assertThat(actualCode, is(expectedCode));
	}

	@Test
	public void testGenerateClassWithListMemberWithImport() {
		final String expectedCode = Resources.asString(this,
				"classWithListMemberWithImport.txt");
		final JavaModelClass jmc = new JavaModelClass(new ModelClass("Foo"));
		jmc.setJavaClassName("my.test.Foo");
		final JavaModelClassMember jmcm = new JavaModelClassMember(
				new ModelClassMember("bar", "List", "String"));
		jmcm.setJavaType(JdkClass.List.getModelClass());
		jmcm.setJavaGenericType(JdkClass.String.getModelClass());
		jmc.importClass(jmcm.getJavaType());
		jmc.importClass(jmcm.getJavaGenericType());
		jmc.add(jmcm);
		final String actualCode = new StringTemplateClassGenerator()
				.generateClass(jmc);
		System.out.println(actualCode);
		assertThat(actualCode, is(expectedCode));
	}
}
