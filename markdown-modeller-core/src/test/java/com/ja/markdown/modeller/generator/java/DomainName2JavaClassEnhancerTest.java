package com.ja.markdown.modeller.generator.java;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.ja.markdown.modeller.model.Model;
import com.ja.markdown.modeller.model.ModelClass;
import com.ja.markdown.modeller.model.ModelClassMember;
import com.ja.markdown.modeller.model.ModelException;

public class DomainName2JavaClassEnhancerTest {

	@Test
	public void testEnhance() throws ModelException {
		final JavaConfig javaConfig = new JavaConfig();
		javaConfig.setProjectPackage("test");

		final Model model = new Model();
		final ModelClass mc = new ModelClass("Foo");
		mc.add(ModelClassMember.create("id: Long"));
		mc.add(ModelClassMember.create("name: String"));
		mc.add(ModelClassMember.create("foo: List[String]"));
		model.addMasterData(mc);
		final JavaModel javaModel = new JavaModel(model);
		final DomainName2JavaClassEnhancer enhancer = new DomainName2JavaClassEnhancer(
				javaConfig);
		enhancer.enhance(javaModel, "junit");
		assertThat(javaModel.getJavaMasterDataClasses().get(0)
				.getJavaClassName(), is("test.junit.Foo"));
		assertThat(javaModel.getJavaMasterDataClasses().get(0).getJavaMembers()
				.get(0).getJavaType(), is(JdkClass.Long.getModelClass()));
		assertThat(javaModel.getJavaMasterDataClasses().get(0).getJavaMembers()
				.get(1).getJavaType(), is(JdkClass.String.getModelClass()));

		assertThat(javaModel.getJavaMasterDataClasses().get(0).getJavaMembers()
				.get(2).getJavaType(), is(JdkClass.List.getModelClass()));
		assertThat(javaModel.getJavaMasterDataClasses().get(0).getJavaMembers()
				.get(2).getJavaGenericType(),
				is(JdkClass.String.getModelClass()));

	}
}
