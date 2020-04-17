package com.pafProject.HealthManagement;

import java.util.HashMap;
import java.util.Map;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;

public class StripeProcessHandle {

	public String createUser(String email, String name) {
		
		String output = "";
		
		com.stripe.Stripe.apiKey  = "";
		
		try {
			
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(
		  "email", email);
		params.put("name", name);
		
			Customer newcust = Customer.create(params);
			output = "Stripe account created";
			
		} catch (StripeException e) {
			output = "Stripe accountnot inserted";
			e.printStackTrace();
		}
		return output;
	}

}
