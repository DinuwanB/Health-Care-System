package com.pafProject.HealthManagement;

import java.util.HashMap;
import java.util.Map;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Token;

public class StripeProcessHandle {
	String output = "";

	public String createUser(String email, String name) {
		
		com.stripe.Stripe.apiKey  = "";
		
		try {
			
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(
		  "email", email);
		params.put("name", name);
		
			Customer newcust = Customer.create(params);
			output = "Stripe account created";
			
		} catch (StripeException e) {
			output = "Stripe account not inserted";
			e.printStackTrace();
		}
		return output;
	}

	public String addCardtoUser(int number, int exp_month, int exp_year, int cvc) throws StripeException {
		
		com.stripe.Stripe.apiKey  = "";
		try {
		Customer customer1 = Customer.retrieve("cus_H7HwpfpTcDgdaC");
		Map<String, Object> cardparams = new HashMap<String, Object>();
		
		cardparams.put("number", "4242424242424242");
		cardparams.put("exp_month", "10");
		cardparams.put("exp_year", "2022");
		cardparams.put("cvc", "145");
		
		Map<String, Object> tokenparams = new HashMap<String, Object>();
		tokenparams.put("card", cardparams);
		
		Token token = Token.create(tokenparams);
		
		Map<String, Object> source = new HashMap<String, Object>();
		source.put("source", token.getId());
		
		output = "Card added to stripe account";
		
		customer1.getSources().create(source);
		} catch (StripeException e) {
			output = "Card not inserted to Stripe account";
			e.printStackTrace();
		}
		
		return output;
	}

}
