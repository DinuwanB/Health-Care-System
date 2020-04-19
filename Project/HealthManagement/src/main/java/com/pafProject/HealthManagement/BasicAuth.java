package com.pafProject.HealthManagement;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Base64;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class BasicAuth implements ContainerRequestFilter {
	
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PERFIX = "Basic ";
	private static final String SECURED_URL_PERFIX = "Customer";
	
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		if(requestContext.getUriInfo().getPath().contains(SECURED_URL_PERFIX)) {
			
		List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
		if(authHeader != null && authHeader.size() > 0) {
			String authToken = authHeader.get(0);
			authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PERFIX, "");
			Base64.Decoder decoder = Base64.getDecoder();  
			String decodeString = new String(decoder.decode(authToken)) ;
			StringTokenizer tokenizer = new StringTokenizer(decodeString, ":");
			String username = tokenizer.nextToken();
			String password = tokenizer.nextToken();
			
			if("960321436V".equals(username) && "password".equals(password)) {
				return;
			}
			
		}
		Response unauthorizedStatus = Response
									  .status(Response.Status.UNAUTHORIZED)
									  .entity("User cannot access the resource -- PLEASE provide valid ID and Password")
									  .build();
		
		requestContext.abortWith(unauthorizedStatus);
		
	}

	}
}
