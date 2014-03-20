package com.ja.markdown.modeller.sc.java.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BeansXml extends TextResource {

	private final String directory;

	private BeansXml(final String directory) {
		this.directory = directory;
		setName("beans.xml");
	}

	@Override
	public String getFileName() {
		return directory + "/" + getName();
	}

	public static BeansXml createMetaInf() {
		return new BeansXml("META-INF");
	}

	public static BeansXml createWebInf() {
		return new BeansXml("WEB-INF");
	}
}
