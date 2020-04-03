package com.airtel.airtelwebsite.core.wcm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.airtel.airtelwebsite.wcm.beans.MultifieldItem;

public class MultifieldDemo extends WCMUsePojo {

	private static Logger logger = LoggerFactory.getLogger(MultifieldDemo.class);
	private List<MultifieldItem> multifieldItems = new ArrayList<>();
	
	@Override
	public void activate() throws Exception {
		
		populateListUsingSling();
		//populateListUsingJCR();

	}

	private void populateListUsingJCR() throws PathNotFoundException, ValueFormatException, RepositoryException {
		logger.debug("populating list using JCR API");
		Node multiFieldParentNode = getResource().adaptTo(Node.class);
		if(multiFieldParentNode.hasNode("multifield")) {
			Node multiFieldNode = multiFieldParentNode.getNode("multifield");
			if(multiFieldNode.hasNodes()) {
				NodeIterator multiFieldNodes = multiFieldNode.getNodes();
				while(multiFieldNodes.hasNext()) {
					MultifieldItem multiFieldItem = new MultifieldItem();
					Node itemNode = multiFieldNodes.nextNode();
					String title = itemNode.getProperty("title").getString();
					String description = itemNode.getProperty("description").getString();
					String imagepath = itemNode.getProperty("imagepath").getString();
					
					multiFieldItem.setTitle(title.toUpperCase());
					multiFieldItem.setDescription(description.toUpperCase());
					multiFieldItem.setImagepath(imagepath);
					multifieldItems.add(multiFieldItem);
				}
			}
		}
		
	}

	private void populateListUsingSling() {
		logger.debug("populating list using Sling API");
		Resource multiField = getResource().getChild("multifield");
		Iterator<Resource> resourceItems = multiField.listChildren();
		
		while(resourceItems.hasNext()) {
			MultifieldItem multiFieldItem = new MultifieldItem();
			Resource childNode = resourceItems.next();
			String title = childNode.getValueMap().get("title", String.class);
			String desc = childNode.getValueMap().get("description", String.class);
			String imagePath = childNode.getValueMap().get("imagepath", String.class);
			
			multiFieldItem.setTitle(title.toUpperCase());
			multiFieldItem.setDescription(desc.toUpperCase());
			multiFieldItem.setImagepath(imagePath);
			multifieldItems.add(multiFieldItem);
		}
		
	}
	
	public List<MultifieldItem> getMultifieldItems() {
		return multifieldItems;
	}

}
