package com.ja.markdown.modeller.sc.java.maven.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import lombok.Data;

@Data
public class PluginConfiguratoin {

	public Map<String, String> config = new HashMap<>();

	public void add(final String key, final String value) {
		config.put(key, value);
	}

	public Set<Map.Entry<String, String>> getItems() {
		return config.entrySet();
	}
}
