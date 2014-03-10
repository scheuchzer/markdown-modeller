package com.ja.markdown.modeller.sc.java.jpa;

import com.ja.markdown.modeller.sc.java.model.PersistenceXml;

public class JpaProperties {

	public static final String DRIVER = "javax.persistence.jdbc.driver";
	public static final String URL = "javax.persistence.jdbc.url";
	public static final String USER = "javax.persistence.jdbc.user";
	public static final String PASSWORD = "javax.persistence.jdbc.password";

	private final PersistenceXml persistence;

	public JpaProperties(final PersistenceXml persistence) {
		this.persistence = persistence;
	}

	public void setDriver(final String value) {
		persistence.addProperty(DRIVER, value);
	}

	public void setUrl(final String value) {
		persistence.addProperty(URL, value);
	}

	public void setUser(final String value) {
		persistence.addProperty(USER, value);
	}

	public void setPassword(final String value) {
		persistence.addProperty(PASSWORD, value);
	}

}
