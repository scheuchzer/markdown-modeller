package com.ja.markdown.modeller.generator.java;

import org.junit.Test;

import com.ja.markdown.modeller.model.Model;
import com.ja.markdown.modeller.model.ModelClass;
import com.ja.markdown.modeller.model.ModelClassMember;
import com.ja.markdown.modeller.model.ModelException;

public class JavaGeneratorTest {

	@Test
	public void test() throws ModelException {
		final Model model = new Model();
		final ModelClass mc = new ModelClass("Foo");
		mc.add(ModelClassMember.create("id: Long"));
		mc.add(ModelClassMember.create("name: String"));
		mc.add(ModelClassMember.create("foo: List[String]"));
		model.addMasterData(mc);
		final JavaGenerator generator = new JavaGenerator();
		generator.process(model);
	}
}
