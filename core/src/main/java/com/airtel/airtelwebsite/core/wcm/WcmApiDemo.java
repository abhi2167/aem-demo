package com.airtel.airtelwebsite.core.wcm;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;

public class WcmApiDemo extends WCMUsePojo {

	private String formattedText;
	private Map<String, Object> currentResourceProps = new HashMap<>();
	private String externalResourceProp;
	private String currentPageTitle;
	private String extPageTitle;
	private String jcrNodeProp;
	
	@Override
	public void activate() throws Exception {
		formattedText = getProperties().get("textField", String.class).toUpperCase();
		
		ValueMap valueMap = getResource().adaptTo(ValueMap.class);
		
		for(Entry<String, Object> entry : valueMap.entrySet()) {
			currentResourceProps.put(entry.getKey(), entry.getValue());
		}
		
		// Sling API
		Resource resource = getResourceResolver()
								.getResource("/content/airtelwebsite/en/jcr:content/root/responsivegrid/helloworld");
		externalResourceProp = resource.getValueMap().get("helloworldProp", String.class);
		
		
		// Page API 
		currentPageTitle = getCurrentPage().getTitle();
		
		Resource extPageRes = getResourceResolver().getResource("/content/airtelwebsite/fr");
		Page extPage = extPageRes.adaptTo(Page.class);
		extPageTitle = extPage.getTitle();
		
		
		
		
		// JCR API
//		Session session = getResourceResolver().adaptTo(Session.class);
//		Node jcrNode = session.getNode("/content/airtelwebsite/en/jcr:content/root/responsivegrid/helloworld");
//		jcrNodeProp = jcrNode.getProperty("helloworldProp").getString();
//		
//		//always close resource Resolver
//		//getResourceResolver().close();
//		
//		// Save and Logout From Session
//		session.save();
//		session.logout();
	}

	public String getFormattedText() {
		return formattedText;
	}

	public Map<String, Object> getCurrentResourceProps() {
		return currentResourceProps;
	}

	public String getExternalResourceProp() {
		return externalResourceProp;
	}

	public String getCurrentPageTitle() {
		return currentPageTitle;
	}

	public String getExtPageTitle() {
		return extPageTitle;
	}

	public String getJcrNodeProp() {
		return jcrNodeProp;
	}
	
	
}
