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

	private final JavaClass extendsType;
	private final List<JavaAnnotation> annotations = new ArrayList<>();
	private final List<JavaMethod> methods = new ArrayList<>();
	private final List<JavaMember> members = new ArrayList<>();
	private final Set<JavaClass> imports = new HashSet<>();
	private List<JavaClass> usedClasses = new ArrayList<>();
	private final ModelClass model;
	private JavaClass genericType;

	private boolean imported;

	public JavaClass(final String pkg, final ModelClass model) {
		this.model = model;
		this.extendsType = null;
		setName(pkg + "." + model.getName());
		for (final ModelClassMember mcm : model.getMembers()) {
			members.add(new JavaMember(mcm));
		}
	}

	public boolean isGeneric() {
		return genericType != null;
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
		this(name, (JavaClass) null);
	}

	public JavaClass(final String name, final JavaClass extendsType) {
		this(name, extendsType, null);
	}

	private JavaClass(final String name, final JavaClass extendsType,
			final ModelClass model) {
		setName(name);
		this.model = model;
		this.extendsType = extendsType;
	}

	public JavaClass(final JavaClass entity) {
		this(entity.getName(), entity.extendsType == null ? null
				: new JavaClass(entity.extendsType), entity.getModel());

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

	public void add(final JavaMethod method) {
		methods.add(method);
	}

	public void addUsedClass(final JavaClass jc) {
		usedClasses.add(jc);
	}

	public void importClass(final JavaClass jc) {
		imports.add(jc);
	}

	public String getSourceCodeTypeName() {
		return imported ? getSimpleName() : getName();
	}

	public String getCamelCaseSimpleName() {
		char c = getSimpleName().charAt(0);
		c = Character.toLowerCase(c);
		return c + getSimpleName().substring(1);
	}

	@Override
	public String getFileName() {
		return getName().replace(".", "/") + ".java";
	}

	public void add(final JavaMember dao) {
		members.add(dao);

	}

}
