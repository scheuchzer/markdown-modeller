package com.ja.markdown.modeller.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ModelObject {

	private List<ModelException> exceptions = new ArrayList<>();

	public void add(final ModelException e) {
		exceptions.add(e);
	}

}
