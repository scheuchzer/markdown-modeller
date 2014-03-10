package com.ja.markdown.modeller.sc.java.jpa.hibernate;

import com.ja.markdown.modeller.sc.java.model.PersistenceXml;

public class HibernateProperties {

	public static final String HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	public static final String DIALECT = "hibernate.dialect";
	public static final String SHOW_SQL = "hibernate.show_sql";
	public static final String FORMAT_SQL = "hibernate.format_sql";

	private final PersistenceXml persistence;

	public HibernateProperties(final PersistenceXml persistence) {
		this.persistence = persistence;
	}

	public void setTableGeneration(final String value) {
		persistence.addProperty(HBM2DDL_AUTO, value);
	}

	public void setDialect(final String value) {
		persistence.addProperty(DIALECT, value);
	}

	public void setShowSql(final boolean value) {
		persistence.addProperty(SHOW_SQL, Boolean.toString(value));
	}

	public void setFormatSq(final boolean value) {
		persistence.addProperty(FORMAT_SQL, Boolean.toString(value));
	}
}
