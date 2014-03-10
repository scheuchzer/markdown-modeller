package com.ja.markdown.modeller.sc.java.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
public class TextResource extends Resource {

	private String content;

	@Override
	public InputStream getContent() {
		try {
			return content == null ? new ByteArrayInputStream(new byte[0])
					: new ByteArrayInputStream(content.getBytes("UTF-8"));
		} catch (final Exception e) {
			throw new RuntimeException("Cant read content of file: "
					+ getName(), e);
		}
	}

	public String getText() {
		return content;
	}

	@Override
	public String getFileName() {
		return getName();
	}
}
