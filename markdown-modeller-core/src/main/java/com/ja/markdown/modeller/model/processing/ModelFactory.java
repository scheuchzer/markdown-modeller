package com.ja.markdown.modeller.model.processing;

import static com.ja.markdown.modeller.MarkdownSyntax.DOMAIN_MODEL;
import lombok.extern.slf4j.Slf4j;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ja.markdown.modeller.model.Model;

@Slf4j
public class ModelFactory {

	public Model create(final Document doc) {
		final Model model = new Model();

		final NodeList h2List = doc.getElementsByTagName("h2");
		for (int i = 0; i < h2List.getLength(); i++) {
			final Node node = h2List.item(i);
			processH2(node, model);
		}
		return model;
	}

	private void processH2(final Node node, final Model model) {
		final String header = node.getTextContent().toLowerCase();
		switch (header) {
		case DOMAIN_MODEL:
			new DomainModel().process(node, model);
			break;
		default:
			log.info("Skipping h2={}", header);
		}

	}
}
