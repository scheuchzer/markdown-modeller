package com.ja.markdown.modeller.sc.java.model;

import java.util.ArrayList;
import java.util.List;

import com.ja.markdown.modeller.model.DomainModel;
import com.ja.markdown.modeller.model.ModelClass;
import com.ja.markdown.modeller.sc.java.JavaClassFactory;

public class JavaDomainModelCloner {

	private final JavaClassFactory javaClassFactory;

	public JavaDomainModelCloner(final String targetPackage) {
		javaClassFactory = new JavaClassFactory(targetPackage);
	}

	public JavaDomainModel clone(final String pkg,
			final DomainModel domainModel, final String projectPackage) {

		return new JavaDomainModel(clone(pkg,
				domainModel.getMasterDataClasses()), clone(pkg,
				domainModel.getBusinessObjectClasses()));
	}

	private List<JavaClass> clone(final String pkg,
			final List<ModelClass> modelClasses) {
		final List<JavaClass> classes = new ArrayList<>();
		for (final ModelClass mc : modelClasses) {
			classes.add(new JavaClass(pkg, mc));
		}
		for (final JavaClass jc : classes) {
			updateMemberTypes(jc);
		}
		return classes;
	}

	private void updateMemberTypes(final JavaClass jc) {
		for (final JavaMember m : jc.getMembers()) {
			m.setClassType(javaClassFactory.resolve(m.getModelClassMember()
					.getType()));
			m.getClassType().setGenericType(
					javaClassFactory.resolve(m.getModelClassMember()
							.getGenericType()));
		}

	}
}
