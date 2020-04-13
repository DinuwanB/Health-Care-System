package com.pafProject.HealthManagement;



import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;;

@Path("customer")
public class CustomerResource {
	

	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	
	public List<Customer> getCustomers()
	{
		System.out.println("Employee Get API Called");
		return getCustomers();
		
	}
	
	@GET
	@Path("customer/{id}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	
	public Customer getCustomer() {
		
	}
	

}
