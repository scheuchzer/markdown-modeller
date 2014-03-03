package com.ja.markdown.modeller.model.processing;

import static com.ja.markdown.modeller.MarkdownSyntax.BUSINESS_OBJECTS;
import lombok.extern.slf4j.Slf4j;

import org.w3c.dom.Node;

import com.ja.markdown.modeller.model.Model;

@Slf4j
public class BusinessObjects {

	public void process(final Node node, final Model model) {
		log.info("Processing {}", BUSINESS_OBJECTS);
		Node sibling = node.getNextSibling();
		while (sibling != null && !"h3".equals(sibling.getNodeName())) {
			processNode(sibling, model);
			sibling = sibling.getNextSibling();
		}
	}

	private void processNode(final Node item, final Model model) {
		final String tag = item.getNodeName();
		switch (tag) {
		case "h4":
			processH4(item, model);
			break;
		default:
			log.debug("Skipping tag={}", tag);
		}
	}

	private void processH4(final Node node, final Model model) {
		new BusinessObject().process(node, model);
	}

}
