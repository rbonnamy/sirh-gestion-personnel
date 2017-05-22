package dev.sgp.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class WebUtils {

	public static String getParameterOrSendBadRequest(HttpServletRequest req, HttpServletResponse resp, String parameterName) throws IOException {
		String parameterValue = req.getParameter(parameterName);
		if (parameterValue==null || parameterValue.isEmpty()){
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Le paramètre "+parameterName+" est obligatoire");
		}
		return req.getParameter(parameterName);
	}
	
}
