package com.ja.markdown.modeller.sc.java.stringtemplate;

import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;

import com.ja.markdown.modeller.model.ModelException;
import com.ja.markdown.modeller.sc.java.JavaModelPlugin;
import com.ja.markdown.modeller.sc.java.model.JavaProject;
import com.ja.markdown.modeller.sc.java.model.Resource;
import com.ja.markdown.modeller.sc.java.model.TextResource;

@Slf4j
public class StringTemplateGenerator implements JavaModelPlugin {

	@Override
	public void execute(final JavaProject project) {
		generate(project.getJavaModel().getClasses());
		generate(project.getJavaModel().getTestClasses());
		generate(project.getMetaInfResources());
		generate(project.getTestMetaInfResources());
	}

	public void generate(final Set<? extends Resource> resources) {
		for (final Resource r : resources) {
			if (r instanceof TextResource) {
				final TextResource tr = (TextResource) r;
				if (tr.getText() == null) {
					final String sc = generateClass(tr);
					tr.setContent(sc);
				} else {
					log.info("Content for {} already set.", tr.getName());
				}
			}
		}
	}

	public String generateClass(final TextResource object) {
		log.info("Creating code for {}: {}", object.getClass().getSimpleName(),
				object.getName());
		/*
		 * Important: The st dir MUSTN'T exisist on the test class path.
		 * STGroupDir only tries the first location found on the classpath!
		 */
		final String objectName = object.getClass().getSimpleName();
		final STGroup group = new STGroupDir(object.getClass().getPackage()
				.getName().replace(".", "/")
				+ "/st");
		final ST st = group.getInstanceOf(objectName);
		if (st == null) {
			log.error("Template for {} not found.", objectName);
			object.add(new ModelException("No template for " + objectName));
		}
		st.add("model", object);
		final String code = st.render();
		log.debug("Generated code:\n{}", code);
		return code;
	}
}
