package com.ja.markdown.modeller.sc.java.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import com.ja.markdown.modeller.model.ModelException;

@Data
public class JavaBase {

	private final List<ModelException> errors = new ArrayList<>();

	public void add(final ModelException e) {
		errors.add(e);
	}
}
