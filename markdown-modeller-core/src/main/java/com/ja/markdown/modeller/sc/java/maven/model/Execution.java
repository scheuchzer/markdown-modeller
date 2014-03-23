package com.ja.markdown.modeller.sc.java.maven.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Execution {

	private final List<Goal> goals = new ArrayList<>();

	private final PluginConfiguratoin configuration = new PluginConfiguratoin();

	public void addGoal(final String goal) {
		goals.add(new Goal(goal));
	}

	public void add(final Goal g) {
		goals.add(g);
	}
}
