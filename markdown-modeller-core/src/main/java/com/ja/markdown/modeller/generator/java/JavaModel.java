package com.ja.markdown.modeller.generator.java;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ja.markdown.modeller.model.Model;
import com.ja.markdown.modeller.model.ModelClass;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JavaModel extends Model {

	public JavaModel(final Model model) {
		for (final ModelClass mc : model.getBusinessObjectClasses()) {
			addBusinessObjectClass(new JavaModelClass(mc));
		}
		for (final ModelClass mc : model.getMasterDataClasses()) {
			addMasterData(new JavaModelClass(mc));
		}

		setExceptions(model.getExceptions());

	}

	@SuppressWarnings("unchecked")
	public List<JavaModelClass> getJavaMasterDataClasses() {
		final Object o = super.getMasterDataClasses();
		return (List<JavaModelClass>) o;
	}

	@SuppressWarnings("unchecked")
	public List<JavaModelClass> getJavaBusinessObjectClasses() {
		final Object o = super.getBusinessObjectClasses();
		return (List<JavaModelClass>) o;
	}
}
