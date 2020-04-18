package com.pafProject.HealthManagement;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

	CustomerDataModel cusRepo = new CustomerDataModel();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Customer> GetCustomers() {
		System.out.println("Patient Get API Called");
		return cusRepo.getCustomers();

	}

	@GET
	@Path("Customer/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Customer GetCustomer(@PathParam("id") int id) {
		System.out.println("Patient 1 Get API Called");
		return cusRepo.getCustomer(id);
	}

	@POST
	@Path("Customer")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Customer CreateUser(Customer cus1) {
		System.out.println("Patient Create API Called");
		cusRepo.createUser(cus1);

		return cus1;
	}

	@PUT
	@Path("Customer")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Customer Update(Customer cus1) {
		System.out.println("Patient Update API Called");
		cusRepo.updateUser(cus1);

		return cus1;
	}

	@DELETE
	@Path("Customer/{id}")
	public Customer DeleteProfile(@PathParam("id") int id) {
		System.out.println("Patient Delete API Called");
		Customer del_cus = cusRepo.getCustomer(id);
		if (del_cus.getCustomerId() != 0) {
			cusRepo.delete(id);
		}
		return del_cus;
	}

}
