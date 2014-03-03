package com.ja.markdown.modeller.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Model extends ModelObject {

	private List<ModelClass> masterDataClasses = new ArrayList<>();
	private List<ModelClass> businessObjectClasses = new ArrayList<>();

	private List<Generator> generators = new ArrayList<>();

	public void addMasterData(final ModelClass clazz) {
		if (masterDataClasses.contains(clazz)
				|| businessObjectClasses.contains(clazz)) {
			add(new ModelException("Duplicate class: " + clazz));
		} else {
			masterDataClasses.add(clazz);
		}
	}

	public void addBusinessObjectClass(final ModelClass clazz) {
		if (businessObjectClasses.contains(clazz)
				|| businessObjectClasses.contains(clazz)) {
			add(new ModelException("Duplicate class: " + clazz));
		} else {
			businessObjectClasses.add(clazz);
		}
	}

	public void addGenerator(final Generator generator) {
		if (generators.contains(generator)) {
			add(new ModelException("Duplicate generator: " + generator));
		} else {
			generators.add(generator);
		}
	}
}
