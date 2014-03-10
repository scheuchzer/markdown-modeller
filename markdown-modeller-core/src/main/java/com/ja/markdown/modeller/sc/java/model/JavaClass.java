package com.ja.markdown.modeller.sc.java.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.apache.commons.lang3.StringUtils;

import com.ja.markdown.modeller.model.ModelClass;
import com.ja.markdown.modeller.model.ModelClassMember;

@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
public class JavaClass extends TextResource {

	private final List<JavaAnnotation> annotations = new ArrayList<>();
	private final List<JavaMethod> methods = new ArrayList<>();
	private final List<JavaMember> members = new ArrayList<>();
	private final Set<JavaClass> imports = new HashSet<>();
	private final ModelClass model;
	private boolean imported;

	public JavaClass(final String pkg, final ModelClass model) {
		this.model = model;
		setName(pkg + "." + model.getName());
		for (final ModelClassMember mcm : model.getMembers()) {
			members.add(new JavaMember(mcm));
		}
	}

	/**
	 * Returns true if a class belongs to the java.lang package whose classes
	 * gets imported implicitly.
	 * 
	 * @return
	 */
	public boolean isDefaultImport() {
		return getPackage().equals(String.class.getPackage().getName());
	}

	public JavaClass(final String name) {
		setName(name);
		this.model = null;
	}

	public String getPackage() {
		return StringUtils.substringBeforeLast(getName(), ".");
	}

	public String getSimpleName() {
		return StringUtils.substringAfterLast(getName(), ".");
	}

	public void add(final JavaAnnotation annotation) {
		annotations.add(annotation);
	}

	public void importClass(final JavaClass jc) {
		imports.add(jc);
	}

	public String getSourceCodeTypeName() {
		return imported ? getSimpleName() : getName();
	}

	public void addAnnotation(final JavaAnnotation javaAnnotation) {
		annotations.add(javaAnnotation);
	}

	@Override
	public String getFileName() {
		return getName().replace(".", "/") + ".java";
	}

}
