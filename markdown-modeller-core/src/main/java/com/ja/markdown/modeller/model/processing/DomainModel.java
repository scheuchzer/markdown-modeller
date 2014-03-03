package com.ja.markdown.modeller.model.processing;

import static com.ja.markdown.modeller.MarkdownSyntax.BUSINESS_OBJECTS;
import static com.ja.markdown.modeller.MarkdownSyntax.DOMAIN_MODEL;
import static com.ja.markdown.modeller.MarkdownSyntax.MASTER_DATA;
import lombok.extern.slf4j.Slf4j;

import org.w3c.dom.Node;

import com.ja.markdown.modeller.model.Model;

@Slf4j
public class DomainModel {

	public void process(final Node node, final Model model) {
		log.info("Processing {}", DOMAIN_MODEL);
		Node sibling = node.getNextSibling();
		while (sibling != null && !"h2".equals(sibling.getNodeName())) {
			processNode(sibling, model);
			sibling = sibling.getNextSibling();
		}

	}

	private void processNode(final Node node, final Model model) {
		final String tag = node.getNodeName();
		switch (tag) {
		case "h3":
			processH3(node, model);
			break;
		default:
			log.debug("Skipping tag={}", tag);
		}
	}

	private void processH3(final Node item, final Model model) {
		final String header = item.getFirstChild().getNodeValue().toLowerCase();
		switch (header) {
		case MASTER_DATA:
			new MasterData().process(item, model);
			break;
		case BUSINESS_OBJECTS:
			new BusinessObjects().process(item, model);
			break;
		default:
			log.debug("Skipping header={}", header);
		}

	}
}
