package com.ja.markdown.modeller.generator.java;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.apache.commons.lang3.text.WordUtils;

import com.ja.markdown.modeller.model.ModelClassMember;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JavaModelClassMember extends ModelClassMember {

	private JavaModelClass javaType;

	private JavaModelClass javaGenericType;

	public JavaModelClassMember(final ModelClassMember mcm) {
		super(mcm.getName(), mcm.getType(), mcm.getGenericType());
	}

	public String getSetterName() {
		return "set" + WordUtils.capitalize(getName());
	}

	public String getGetterName() {
		return "get" + WordUtils.capitalize(getName());
	}
}
