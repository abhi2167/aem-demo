package com.airtel.airtelwebsite.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.airtel.airtelwebsite.core.service.MathService;
import com.day.cq.wcm.api.Page;

//@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
// We can also adapt to SlingHttpServletRequest, which has access to more properties
// exposing functionality via interface is good practise
@Model(adaptables = SlingHttpServletRequest.class, 
	defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
	adapters = IAirtelNewModel.class,
	resourceType = "airtelnew/components/content/bannercomponent")
@Exporter(name = "jackson", extensions="json", selector = "custom")
public class AirtelNewModel implements IAirtelNewModel {

	@Inject
	@Named(value = "title")
	@Via("resource")
	private String title;
	
	@ValueMapValue(name = "description", injectionStrategy = InjectionStrategy.OPTIONAL, via = "resource")
	private String description;
	
	//@Inject
	@ScriptVariable
	private Page currentPage;
	
	@SlingObject
	private ResourceResolver resourceResolver;
	
	@SlingObject
	private Resource resource;
	
	private int totalValue;
	
	@Inject
	MathService mathService;
	
	public String getTitle() {
		//return title.toUpperCase();
		resource = resourceResolver.getResource("/content/airtelwebsite/fr");
		Page extPage = resource.adaptTo(Page.class);
		this.title = this.currentPage.getName() + " " + extPage.getTitle();
		
		return this.title;
	}
	public String getDescription() {
		return description;
	}
	
	@PostConstruct
	private void init() {
		this.description = description.toLowerCase();
	}
	public int getTotalValue() {
		totalValue = this.mathService.calculateValue();
		return totalValue;
	}
}
