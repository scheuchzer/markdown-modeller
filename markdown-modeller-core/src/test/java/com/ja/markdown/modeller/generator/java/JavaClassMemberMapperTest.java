package com.ja.markdown.modeller.generator.java;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.ja.markdown.modeller.generator.GeneratorException;
import com.ja.markdown.modeller.model.ModelClass;
import com.ja.markdown.modeller.model.ModelClassMember;
import com.ja.markdown.modeller.model.ModelException;

public class JavaClassMemberMapperTest {

	@Test
	public void testMapJavaLangClasses() throws ModelException,
			GeneratorException {
		final JavaConfig javaConfig = new JavaConfig();
		javaConfig.setProjectPackage("com.ja.example");

		final ModelClass mc = ModelClass.create("MyClass");
		mc.add(ModelClassMember.create("my string: String"));
		mc.add(ModelClassMember.create("my integer: Integer"));
		mc.add(ModelClassMember.create("my float: Float"));
		final JavaModelClass jmc = new JavaModelClass(mc);

		final NameMap nameMap = new NameMap();

		final JavaClassMemberMapper mapper = new JavaClassMemberMapper(nameMap);
		mapper.map(jmc);

		assertThat(jmc.getJavaMembers().get(0).getJavaType(),
				is(JdkClass.String.getModelClass()));
		assertThat(jmc.getJavaMembers().get(1).getJavaType(),
				is(JdkClass.Integer.getModelClass()));
		assertThat(jmc.getJavaMembers().get(2).getJavaType(),
				is(JdkClass.Float.getModelClass()));
	}

	@Test
	public void testMapList() throws ModelException, GeneratorException {
		final JavaConfig javaConfig = new JavaConfig();
		javaConfig.setProjectPackage("com.ja.example");

		final ModelClass mc = ModelClass.create("MyClass");
		mc.add(ModelClassMember.create("my string: List[String]"));
		final JavaModelClass jmc = new JavaModelClass(mc);

		final NameMap nameMap = new NameMap();

		final JavaClassMemberMapper mapper = new JavaClassMemberMapper(nameMap);
		mapper.map(jmc);

		assertThat(jmc.getJavaMembers().get(0).getJavaType(),
				is(JdkClass.List.getModelClass()));
		assertThat(jmc.getJavaMembers().get(0).getJavaGenericType(),
				is(JdkClass.String.getModelClass()));
	}
}
