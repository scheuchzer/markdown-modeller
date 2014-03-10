package com.ja.markdown.modeller.sc.java.model;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PersistenceXml extends MetaInf {

	public static final String NAME = "persistence.xml";

	private String unitName;
	private String transactionType;
	private String jtaDataSource;
	private boolean excludeUnlistedClasses;
	private Set<JavaClass> classes = new HashSet<>();

	private Map<String, String> properties = new HashMap<>();

	public PersistenceXml() {
		setName(NAME);
	}

	public void addProperty(final String key, final String value) {
		properties.put(key, value);
	}

	public Collection<Map.Entry<String, String>> getProperties() {
		final TreeSet<Map.Entry<String, String>> set = new TreeSet<>(
				new Comparator<Map.Entry<String, String>>() {

					@Override
					public int compare(final Entry<String, String> o1,
							final Entry<String, String> o2) {
						return o1.getKey().compareTo(o2.getKey());
					}
				});
		set.addAll(properties.entrySet());
		return set;
	}

	public void add(final JavaClass jc) {
		classes.add(jc);
	}

}
