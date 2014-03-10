package com.ja.markdown.modeller.sc.java.maven;

import org.junit.Test;

import com.ja.markdown.modeller.model.DomainModel;
import com.ja.markdown.modeller.model.ModelClass;
import com.ja.markdown.modeller.model.ModelClassMember;
import com.ja.markdown.modeller.model.ModelException;
import com.ja.markdown.modeller.model.Project;
import com.ja.markdown.modeller.sc.java.jpa.JpaModelEnhancer;
import com.ja.markdown.modeller.sc.java.lombok.LombokEnhancer;
import com.ja.markdown.modeller.sc.java.model.JavaProject;
import com.ja.markdown.modeller.sc.java.stringtemplate.StringTemplateGenerator;

public class MavenGeneratorTest {

	@Test
	public void execute() throws ModelException {
		final Project project = new Project();
		project.setTitle("My Project");
		project.setAkaName("my-proj");
		project.setGroupId("foo.bar");

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

		final JavaProject javaProject = new JavaProject(project);
		new JpaModelEnhancer().execute(javaProject);
		new LombokEnhancer().execute(javaProject);
		new StringTemplateGenerator().execute(javaProject);

		final MavenGenerator generator = new MavenGenerator();
		generator.execute(javaProject);
	}
}
