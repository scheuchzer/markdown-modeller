package com.ja.markdown.modeller.model;

import java.io.File;

import lombok.Data;

@Data
public class Project {

	private String title;
	private String akaName;
	private String groupId;

	private final File outputFolder;

	private final DomainModel domainModel = new DomainModel();

	public Project() {
		this.outputFolder = new File(".");
	}

}
