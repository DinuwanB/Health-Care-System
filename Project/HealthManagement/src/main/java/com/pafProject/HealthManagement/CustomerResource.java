package com.pafProject.HealthManagement;



import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;;

@Path("Customer")
public class CustomerResource {
	
	CustomerDataModel cusRepo =  new CustomerDataModel();
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Customer> getCustomers()
	{
		System.out.println("Employee Get API Called");
		return cusRepo.getCustomers();
		
	}
	
	@GET
	@Path("Customer/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Customer getCustomer(@PathParam("id") String id) 
	{
		System.out.println("Employeess Get API Called");
		return cusRepo.getCustomer(id);
	}
	
	@POST
	@Path("Customer")
	public Customer createCustomer(Customer c1)
	{
		System.out.println(c1);
		cusRepo.create(c1);
		
		return c1;
		
	}
	

}
