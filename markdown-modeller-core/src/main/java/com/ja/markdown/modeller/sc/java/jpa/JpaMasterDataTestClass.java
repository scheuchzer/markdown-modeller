package com.ja.markdown.modeller.sc.java.jpa;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import com.ja.markdown.modeller.sc.java.model.JavaClass;
import com.ja.markdown.modeller.sc.java.model.JavaMember;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Slf4j
public class JpaMasterDataTestClass extends JavaClass {

	private final JavaClass javaClass;

	public JpaMasterDataTestClass(final JavaClass javaClass) {
		super(javaClass.getName() + "Test");
		this.javaClass = javaClass;
	}

	public Set<Map.Entry<String, String>> getSetterMethodCalls() {
		final Map<String, String> methods = new HashMap<>();

		for (final JavaMember m : javaClass.getMembers()) {
			if (m.getAnnotations().toString()
					.contains(JpaClass.Id.instance().getName())) {
				log.info("skipping id member: {}", m.getName());
				continue;
			}
			final String setter = "set" + m.getNameForMethod();
			String value = null;
			if (String.class.getName().equals(m.getClassType().getName())) {
				value = String
						.format("\"%s-%s\"", javaClass.getSimpleName(), 0);
			} else if (Long.class.getName().equals(m.getClassType().getName())) {
				value = "0";
			} else if (Integer.class.getName().equals(
					m.getClassType().getName())) {
				value = "0";
			}
			methods.put(setter, value);
		}
		return methods.entrySet();
	}
}
