package com.ja.markdown.modeller.sc.java.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;

import com.ja.markdown.modeller.model.ModelException;
import com.ja.markdown.modeller.sc.java.JavaModelPlugin;
import com.ja.markdown.modeller.sc.java.model.JavaProject;
import com.ja.markdown.modeller.sc.java.model.Resource;

@Slf4j
public class FileSourceCodeWriter implements JavaModelPlugin {

	@Override
	public void execute(final JavaProject project) {
		write(project.getJavaModel().getClasses(),
				project.getClassesOutputFolder());
		write(project.getJavaModel().getTestClasses(),
				project.getTestClassesOutputFolder());
		write(project.getMetaInfResources(), project.getResourcesOutputFolder());
		write(project.getTestMetaInfResources(),
				project.getTestResourcesOutputFolder());
		write(project.getWebappOutputResources(),
				project.getWebappOutputFolder());
	}

	private void write(final Set<? extends Resource> resources,
			final File outputFolder) {
		for (final Resource r : resources) {
			write(r, outputFolder);
		}
	}

	private void write(final Resource r, final File outputFolder) {
		final String fileName = r.getFileName();
		final File outFile = new File(outputFolder, fileName);
		log.info("Writing class to file: {} -> {}", r.getName(),
				outFile.getAbsolutePath());
		outFile.getParentFile().mkdirs();
		try (OutputStream os = new FileOutputStream(outFile)) {
			IOUtils.copy(r.getContent(), os);
		} catch (final Exception e) {
			r.add(new ModelException("Couldn't write source code file "
					+ outFile.getAbsolutePath(), e));
			e.printStackTrace();
		}
	}

}
