package com.airtel.airtelwebsite.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class, 
property= {
		Constants.SERVICE_DESCRIPTION + "=Airtel Path Servlet",
		"sling.servlet.methods="+ HttpConstants.METHOD_GET,
		"sling.servlet.paths=" + "/bin/path/airtel",
		"sling.servlet.selectors=" + "custom",
        "sling.servlet.extensions=" + "txt"
}
)
// selectors and extensions are not required when defining servlet by path
public class AirtelServletByPath extends SlingSafeMethodsServlet {

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		response.getWriter().write("Airtel Servlet by path");
	}
}
