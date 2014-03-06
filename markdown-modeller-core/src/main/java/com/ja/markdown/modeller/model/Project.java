package com.ja.markdown.modeller.model;

import lombok.Data;

@Data
public class Project {

	private String title;
	private String akaName;
	private String groupId;

	private final DomainModel domainModel = new DomainModel();

}
