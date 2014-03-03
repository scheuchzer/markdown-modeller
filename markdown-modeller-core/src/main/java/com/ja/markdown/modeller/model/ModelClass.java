package com.ja.markdown.modeller.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ModelClass extends ModelObject {

	private final String name;

	private List<ModelClassMember> members = new ArrayList<>();

	public void add(final ModelClassMember member) {
		if (members.contains(member)) {
			add(new ModelException("Class: " + name + ", Duplicate member: "
					+ member));
		} else {
			members.add(member);
		}
	}

	public static ModelClass create(final String name) throws ModelException {
		return new ModelClass(Names.getClassName(name));
	}
}
