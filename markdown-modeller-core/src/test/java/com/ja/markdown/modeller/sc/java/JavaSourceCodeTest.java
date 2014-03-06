package com.ja.markdown.modeller.sc.java;

import org.junit.Test;

import com.ja.markdown.modeller.model.DomainModel;
import com.ja.markdown.modeller.model.ModelClass;
import com.ja.markdown.modeller.model.ModelClassMember;
import com.ja.markdown.modeller.model.ModelException;
import com.ja.markdown.modeller.model.Project;

public class JavaSourceCodeTest {

	@Test
	public void testExecute() throws ModelException {
		final Project project = new Project();
		project.setTitle("Test");
		project.setAkaName("foo-bar");
		project.setGroupId("my");

		final DomainModel domainModel = project.getDomainModel();

		final ModelClass fooType = ModelClass.create("FooType");
		fooType.add(ModelClassMember.create("id: Long"));
		fooType.add(ModelClassMember.create("name: String"));
		final ModelClass foo = ModelClass.create("Foo");
		foo.add(ModelClassMember.create("id: Long"));
		foo.add(ModelClassMember.create("name: String"));
		foo.add(ModelClassMember.create("type: FooType"));
		domainModel.addMasterData(fooType);
		domainModel.addBusinessObjectClass(foo);
		new JavaSourceCode().execute(project);
	}
}
