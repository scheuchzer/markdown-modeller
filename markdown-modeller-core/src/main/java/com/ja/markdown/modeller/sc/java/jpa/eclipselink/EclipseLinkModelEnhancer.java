package com.ja.markdown.modeller.sc.java.jpa.eclipselink;

import com.ja.markdown.modeller.sc.java.JavaModelPlugin;
import com.ja.markdown.modeller.sc.java.model.JavaProject;
import com.ja.markdown.modeller.sc.java.model.PersistenceXml;

public class EclipseLinkModelEnhancer implements JavaModelPlugin {

	@Override
	public void execute(final JavaProject project) {
		final PersistenceXml persistenceXml = project
				.getMetaInfResource(PersistenceXml.NAME);
		addProperties(persistenceXml);

		final PersistenceXml testPersistenceXml = project
				.getTestMetaInfResource(PersistenceXml.NAME);
		addProperties(testPersistenceXml);
	}

	private void addProperties(final PersistenceXml persistenceXml) {
		if (persistenceXml == null) {
			return;
		}
		final EclipseLinkProperties props = new EclipseLinkProperties(
				persistenceXml);
		props.setTableGeneration("create");
	}
}
