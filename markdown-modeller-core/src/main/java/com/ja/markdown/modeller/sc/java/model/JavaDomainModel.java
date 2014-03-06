package com.ja.markdown.modeller.sc.java.model;

import java.util.List;

import lombok.Value;

@Value
public class JavaDomainModel {

	private final List<JavaClass> masterDataClasses;
	private final List<JavaClass> businessDataClasses;

}
