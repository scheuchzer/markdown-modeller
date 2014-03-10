package com.ja.markdown.modeller.sc.java.jpa.hibernate;

import com.ja.markdown.modeller.sc.java.JavaModelPlugin;
import com.ja.markdown.modeller.sc.java.model.JavaProject;
import com.ja.markdown.modeller.sc.java.model.PersistenceXml;

public class HibernateModelEnhancer implements JavaModelPlugin {

	@Override
	public void execute(final JavaProject project) {
		final PersistenceXml persistenceXml = project
				.getMetaInfResource(PersistenceXml.NAME);
		addProperties(persistenceXml);

		final PersistenceXml testPersistenceXml = project
				.getTestMetaInfResource(PersistenceXml.NAME);
		addTestProperties(testPersistenceXml);
	}

	private void addProperties(final PersistenceXml persistenceXml) {
		if (persistenceXml == null) {
			return;
		}
		final HibernateProperties props = new HibernateProperties(
				persistenceXml);
		props.setDialect("org.hibernate.dialect.H2Dialect");
		props.setTableGeneration("create");
		props.setFormatSq(false);
		props.setShowSql(false);
	}

	private void addTestProperties(final PersistenceXml persistenceXml) {
		if (persistenceXml == null) {
			return;
		}
		final HibernateProperties props = new HibernateProperties(
				persistenceXml);
		props.setDialect("org.hibernate.dialect.H2Dialect");
		props.setTableGeneration("create");
		props.setFormatSq(true);
		props.setShowSql(true);
	}
}
