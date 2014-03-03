package com.ja.markdown.modeller.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ModelClassMember extends ModelObject {

	private final String name;
	private final String type;
	private final String genericType;

	public static ModelClassMember create(final String memberDefinition)
			throws ModelException {
		final String name = Names.extractMemberName(memberDefinition);
		final String type = Names.extractMemberClass(memberDefinition);
		final String genericType = Names
				.extractMemberGenericClass(memberDefinition);
		return new ModelClassMember(name, type, genericType);
	}

	public boolean isGeneric() {
		return genericType != null;
	}

}
