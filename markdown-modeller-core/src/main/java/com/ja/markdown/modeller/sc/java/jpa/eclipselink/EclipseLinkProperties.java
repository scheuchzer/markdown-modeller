package com.ja.markdown.modeller.sc.java.jpa.eclipselink;

import com.ja.markdown.modeller.sc.java.model.PersistenceXml;

public class EclipseLinkProperties {

	public static final String DDL_GENERATION = "eclipselink.ddl-generation";

	private final PersistenceXml persistence;

	public EclipseLinkProperties(final PersistenceXml persistence) {
		this.persistence = persistence;
	}

	public void setTableGeneration(final String value) {
		persistence.addProperty(DDL_GENERATION, value);
	}

}
