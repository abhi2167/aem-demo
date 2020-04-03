package com.airtel.airtelwebsite.core.service.impl;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Airtel Service Configuration")
public @interface AirtelProperties {

	@AttributeDefinition(name = "Endpoint URL", description = "Third party URL", type = AttributeType.STRING)
	String getEndpoint() default "http://www.mocky.io/v2/5e7d685e350000d28e06aedf";
	
}
