package com.ja.markdown.modeller.generator.java;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import com.ja.markdown.modeller.generator.GeneratorException;

@Slf4j
@Value
public class DomainName2JavaClassEnhancer {

	private final JavaConfig config;

	public DomainName2JavaClassEnhancer(final JavaConfig config) {
		this.config = config;
	}

	public void enhance(final JavaModel model, final String module)
			throws GeneratorException {
		final JavaClassNameDiscoverer discoverer = new JavaClassNameDiscoverer(
				config);

		discoverer.discover(model.getJavaMasterDataClasses(), module);
		discoverer.discover(model.getJavaBusinessObjectClasses(), module);
		final NameMap nameMap = discoverer.getNameMap();
		new JavaClassMemberMapper(nameMap).map(model);
		log.info("JavaModel: {}", model);
	}

}
