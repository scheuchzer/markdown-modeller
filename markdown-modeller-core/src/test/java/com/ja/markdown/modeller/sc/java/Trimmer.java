package com.ja.markdown.modeller.sc.java;

import org.apache.commons.lang3.StringUtils;

public class Trimmer {

	public static String trim(final String code) {
		return StringUtils.replace(code, "\n", "");
	}
}
