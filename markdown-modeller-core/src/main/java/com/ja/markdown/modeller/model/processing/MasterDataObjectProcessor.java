package com.ja.markdown.modeller.model.processing;

import lombok.extern.slf4j.Slf4j;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ja.markdown.modeller.model.DomainModel;
import com.ja.markdown.modeller.model.ModelClass;
import com.ja.markdown.modeller.model.ModelClassMember;
import com.ja.markdown.modeller.model.ModelException;

@Slf4j
public class MasterDataObjectProcessor {

	public void process(final Node node, final DomainModel model) {
		final String header = node.getFirstChild().getNodeValue().toLowerCase();
		log.info("Master data class={}", header);
		try {
			final ModelClass clazz = ModelClass.create(header);
			model.addMasterData(clazz);
			Node sibling = node.getNextSibling();
			while (sibling != null) {
				if ("ul".equals(sibling.getNodeName())) {
					processAttributes(sibling, clazz);
					break;
				}
				sibling = sibling.getNextSibling();
			}
		} catch (final ModelException e) {
			model.add(e);
			return;
		}
	}

	private void processAttributes(final Node node, final ModelClass clazz) {
		final NodeList lis = node.getChildNodes();
		for (int i = 0; i < lis.getLength(); i++) {
			final Node li = lis.item(i);
			if (!"li".equals(li.getNodeName())) {
				continue;
			}
			final String memberDefinition = li.getFirstChild().getNodeValue();
			ModelClassMember member;
			try {
				member = ModelClassMember.create(memberDefinition);
			} catch (final ModelException e) {
				clazz.add(e);
				continue;
			}
			clazz.add(member);
		}
	}
}
