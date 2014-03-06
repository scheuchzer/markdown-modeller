package com.ja.markdown.modeller.model.processing;

import lombok.extern.slf4j.Slf4j;

import org.w3c.dom.Node;

import com.ja.markdown.modeller.model.DomainModel;

@Slf4j
public class BusinessObjectProcessor {

	public void process(final Node node, final DomainModel model) {
		final String header = node.getFirstChild().getNodeValue().toLowerCase();
		log.info("Business object class={}", header);
	}

}
