package com.ja.markdown.modeller.generator.java;

import java.util.List;

import lombok.Data;

public class LombokEnhancer {

	public void enhance(final JavaModel javaModel) {
		enableLombok(javaModel.getJavaMasterDataClasses());
		enableLombok(javaModel.getJavaBusinessObjectClasses());

	}

	private void enableLombok(final List<JavaModelClass> classes) {
		for (final JavaModelClass jmc : classes) {
			jmc.addClassAnnotation(new CommonJavaClass(Data.class));
		}
	}

}
