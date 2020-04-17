package com.pafProject.HealthManagement;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("payment")
public class Stripe {
	
	StripeProcessHandle stph = new StripeProcessHandle();
	
	@POST
	@Path("customer")
	@Consumes( MediaType.APPLICATION_FORM_URLENCODED)
	public String createUser(@FormParam("email") String  email, @FormParam("name") String name) {
	
		String output = stph.createUser(email,name);
		return output;
		
	}

}
