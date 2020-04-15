package com.pafProject.HealthManagement;



import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;;

@Path("Customer")
public class CustomerResource {
	
	CustomerDataModel cusRepo =  new CustomerDataModel();
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Customer> getCustomers()
	{
		System.out.println("Employee Get API Called");
		return cusRepo.getCustomers();
		
	}
	
	@GET
	@Path("Customer/{NIC}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Customer getCustomer(@PathParam("NIC") String NIC) 
	{
		System.out.println("Employeess 1 Get API Called");
		return cusRepo.getCustomer(NIC);
	}
	
	@POST
	@Path("Customer")
	@Consumes(MediaType.APPLICATION_XML)
	public Customer createUser(Customer cus1)
	{
		System.out.println("Employeess Create API Called");
		cusRepo.createUser(cus1); 
		
		return cus1;
	}
	

}
