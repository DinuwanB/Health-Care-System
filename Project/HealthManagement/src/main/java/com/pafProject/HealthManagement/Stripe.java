package com.pafProject.HealthManagement;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.stripe.exception.StripeException;

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
	
	@POST
	@Path("card/")
	@Consumes( MediaType.APPLICATION_FORM_URLENCODED)
	public String addCard(@FormParam("number") int number , @FormParam("exp_month") int exp_month,
			@FormParam("exp_year") int exp_year, @FormParam("cvc") int cvc) throws StripeException {
		
		String output = stph.addCardtoUser(number,exp_month,exp_year,cvc);
		return output;
	}

}
