package com.pafProject.HealthManagement;



import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;;

@Path("customer")
public class CustomerResource {
	
	CustomerDataModel cusRepo =  new CustomerDataModel();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Customer> getCustomers()
	{
		System.out.println("Employee Get API Called");
		return cusRepo.getCustomers();
		
	}
	
	@GET
	@Path("customer/{id}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Customer getCustomer(@PathParam("id") String id) 
	{
		return cusRepo.getCustomer();
	}
	
	@POST
	@Path("customer")
	public Customer createCustomer()
	{
		
	}
	

}
