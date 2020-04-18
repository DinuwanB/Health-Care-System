package com.pafProject.HealthManagement;

import java.util.HashMap;
import java.util.Map;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;

public class StripeProcessHandle {
	String output = "";
	String output2 = "";

	public String createUser(String cemail, String cname) {
		
		com.stripe.Stripe.apiKey  = "STRIPE_API_KEY";
		
		try {
			
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(
		  "email", cemail);
		params.put("name", cname);
		
			Customer newcust = Customer.create(params);
			output = "Stripe account created";
			
		} catch (StripeException e) {
			output = "Stripe account not inserted";
			e.printStackTrace();
		}
		return output;
	}
	
	public String addCardtoUser(String userId, String number, String exp_month, String exp_year, String cvc)  {
		
		com.stripe.Stripe.apiKey  = "STRIPE_API_KEY";
		try {
		Customer customer1 = Customer.retrieve(userId);
		System.out.println(customer1.getId());
		Map<String, Object> card = new HashMap<String, Object>();
		System.out.println(number);
		card.put("number",number);
		card.put("exp_month",exp_month);
		card.put("exp_year",exp_year);
		card.put("cvc",cvc);
		
		Map<String, Object> tokenparams = new HashMap<String, Object>();
		tokenparams.put("card", card);
		
		Token token = Token.create(tokenparams);
		System.out.println(token);
		
		Map<String, Object> source = new HashMap<String, Object>();
		source.put("source", token.getId());
		
		System.out.println(token.getId());
		//String source = token.getId()
		customer1.getSources().create(source);
		
		output2 = "Card added to stripe account";
		}
		catch (Exception e) {
			System.out.println("card not added");
			output2 = "Card not added to stripe account";
		}
		
		return output2;
	}

	public String payCharge(String cusId, String amount) {
		com.stripe.Stripe.apiKey  = "STRIPE_API_KEY";
		
		try {
		Customer customer1 = Customer.retrieve(cusId);
		System.out.println(customer1.getId());
		System.out.println(amount);
		Map<String, Object> pay = new HashMap<>();
		pay.put("amount", amount);
		pay.put("currency", "lkr");
		pay.put("customer", customer1.getId());
		
		Charge.create(pay);
		output = "payment done";
		
		} 
		catch(Exception e) {
			output = "payment not done";
			System.out.println("payment not added");
		}
		return output;
	}

}
