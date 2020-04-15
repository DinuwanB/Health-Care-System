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
	@Produces(MediaType.APPLICATION_XML)
	public List<Customer> getCustomers()
	{
		System.out.println("Employee Get API Called");
		return cusRepo.getCustomers();
		
	}
	
	@GET
	@Path("Customer/{NIC}")
	@Produces(MediaType.APPLICATION_XML)
	public Customer getCustomer(@PathParam("NIC") String NIC) 
	{
		System.out.println("Employeess 1 Get API Called");
		return cusRepo.getCustomer(NIC);
	}
	
	@POST
	@Path("Customer")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createUser(@FormParam("nic") String nic, @FormParam("firstname") String firstname, @FormParam("lastname") String lastname,
			@FormParam("email") String email, @FormParam("phonenumber") String phonenumber,@FormParam("Birthday") String birthday,
			@FormParam("password") String password )
	{
		System.out.println("Employeess Create API Called");
		String output = cusRepo.createUser(nic,firstname,lastname,email,phonenumber,birthday,password);
		return output; 
		
	}
	

}
