package com.ja.markdown.modeller.sc.java.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;

import com.ja.markdown.modeller.model.ModelException;
import com.ja.markdown.modeller.sc.java.JavaModelPlugin;
import com.ja.markdown.modeller.sc.java.model.JavaClass;
import com.ja.markdown.modeller.sc.java.model.JavaProject;

@Slf4j
public class FileSourceCodeWriter implements JavaModelPlugin {

	@Override
	public void execute(final JavaProject project) {
		final File outputFolder = project.getOutputFolder();
		for (final JavaClass jc : project.getJavaModel().getClasses()) {
			write(jc, outputFolder);
		}
	}

	private void write(final JavaClass jc, final File outputFolder) {

		if (jc.getSourceCode() == null) {
			return;
		}
		final String fileName = jc.getName().replace(".", "/") + ".java";
		final File outFile = new File(outputFolder, fileName);
		log.info("Writing class to file: {} -> {}", jc.getName(),
				outFile.getAbsolutePath());
		outFile.getParentFile().mkdirs();
		try (OutputStream os = new FileOutputStream(outFile)) {
			IOUtils.write(jc.getSourceCode(), os);
		} catch (final Exception e) {
			jc.add(new ModelException("Couldn't write source code file "
					+ outFile.getAbsolutePath(), e));
			e.printStackTrace();
		}
	}

}
