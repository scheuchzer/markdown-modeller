package com.ja.markdown.modeller.sc.java.model;

import java.io.InputStream;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
public abstract class Resource extends JavaBase {

	private String name;

	public abstract InputStream getContent();

	public abstract String getFileName();
}
