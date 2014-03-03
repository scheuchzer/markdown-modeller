package com.ja.markdown.modeller.model;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

@Slf4j
public class Names {

	public static String getClassName(final String name) throws ModelException {
		final String className = StringUtils.remove(
				WordUtils.capitalizeFully(StringUtils.trimToNull(name)), " ");
		log.debug("name={}, className={}", name, className);
		return className;
	}

	public static String extractMemberName(final String memberDefintion)
			throws ModelException {
		final String[] tokens = extractMemberValues(memberDefintion);
		return tokens[0];
	}

	public static String extractMemberClass(final String memberDefintion)
			throws ModelException {
		final String[] tokens = extractMemberValues(memberDefintion);
		return tokens[1];
	}

	public static String extractMemberGenericClass(final String memberDefintion)
			throws ModelException {
		final String[] tokens = extractMemberValues(memberDefintion);
		return tokens[2];
	}

	private static String[] extractMemberValues(final String memberDefintion)
			throws ModelException {
		String[] tokens = StringUtils.split(memberDefintion, ":");
		if (tokens.length == 1) {
			tokens = new String[] { tokens[0], "String", null };
		}
		tokens = new String[] { tokens[0], tokens[1], null };
		tokens[0] = toMemberName(getClassName(tokens[0]));
		tokens[2] = getClassName(StringUtils.substringBetween(tokens[1], "[",
				"]"));
		tokens[1] = getClassName(StringUtils.substringBefore(tokens[1], "["));
		return tokens;
	}

	private static String toMemberName(final String name) {
		return Character.toLowerCase(name.charAt(0))
				+ StringUtils.substring(name, 1);
	}
}
