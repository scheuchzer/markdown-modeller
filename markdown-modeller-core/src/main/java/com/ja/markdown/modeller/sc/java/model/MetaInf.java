package com.ja.markdown.modeller.sc.java.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MetaInf extends TextResource {

	@Override
	public String getFileName() {
		return "META-INF/" + getName();
	}
}
