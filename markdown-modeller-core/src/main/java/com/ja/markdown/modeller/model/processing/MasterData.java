package com.ja.markdown.modeller.model.processing;

import static com.ja.markdown.modeller.MarkdownSyntax.MASTER_DATA;
import lombok.extern.slf4j.Slf4j;

import org.w3c.dom.Node;

import com.ja.markdown.modeller.model.Model;

@Slf4j
public class MasterData {

	public void process(final Node node, final Model model) {
		log.info("Processing {}", MASTER_DATA);
		Node sibling = node.getNextSibling();
		while (sibling != null && !"h3".equals(sibling.getNodeName())) {
			processNode(sibling, model);
			sibling = sibling.getNextSibling();
		}
	}

	private void processNode(final Node node, final Model model) {
		final String tag = node.getNodeName();
		switch (tag) {
		case "h4":
			processH4(node, model);
			break;
		default:
			log.debug("Skipping tag={}", tag);
		}
	}

	private void processH4(final Node node, final Model model) {
		new MasterDataObject().process(node, model);
	}
}
