package com.airtel.airtelwebsite.core.service.impl;

import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;

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
	
	@Activate
	public final void activate(final Map<String, Object> properties) {
		firstValue = (Integer) properties.get("addition.first");
		secondValue = (Integer) properties.get("addition.second");
	}

	@Override
	public int calculateValue() {
		return this.firstValue + this.secondValue;
	}

}
