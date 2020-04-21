package com.airtel.airtelwebsite.core.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Reference;

import com.airtel.airtelwebsite.core.service.MathService;

@Service(value = MathService.class)
@Component(label = "Math Service", metatype = true)
@Properties({
	@Property(name = "addition.first", label = "Enter first Value", description = "First value", intValue = 30),
	@Property(name= "addition.second", label = "Enter second value", description= "Second value", intValue = 20)
})
public class MathServiceImpl implements MathService {

	int firstValue;
	int secondValue;
	
	@Reference
	private ResourceResolverFactory resourceResolverFactory;
	
	private ResourceResolver resourceResolver;
	
	@Activate
	public final void activate(final Map<String, Object> properties) {
		firstValue = (Integer) properties.get("addition.first");
		secondValue = (Integer) properties.get("addition.second");
	}

	@Override
	public int calculateValue() {
		/**
		 * how to access resource resolver from OSGI Services
		 */
		Map<String, Object> authenticationInfo = new HashMap<String, Object>();
		authenticationInfo.put(ResourceResolverFactory.SUBSERVICE, "accessairtel");
		try {
			resourceResolver = resourceResolverFactory.getServiceResourceResolver(authenticationInfo);
			Resource resource = resourceResolver.getResource("/content/airtelwebsite/en/jcr:content/root/responsivegrid/helloworld");
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.firstValue + this.secondValue;
	}

}
