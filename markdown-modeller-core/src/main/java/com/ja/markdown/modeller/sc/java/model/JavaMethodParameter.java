package com.ja.markdown.modeller.sc.java.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
public class JavaMethodParameter extends JavaBase {

	private final String name;

	private final JavaClass type;
}
