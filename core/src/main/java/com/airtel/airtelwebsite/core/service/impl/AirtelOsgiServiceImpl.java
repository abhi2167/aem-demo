package com.airtel.airtelwebsite.core.service.impl;

import org.apache.felix.scr.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import com.airtel.airtelwebsite.core.service.AirtelOsgiService;

@Component(service = AirtelOsgiService.class, name = "Airtel OSGI Service")
@Designate(ocd = AirtelProperties.class)
public class AirtelOsgiServiceImpl implements AirtelOsgiService {

	@Override
	public String invokeThirdPartyJson() {
		return null;
	}

	@Activate
	public final void activate(AirtelProperties airtelProperties) {
		
	}
}
