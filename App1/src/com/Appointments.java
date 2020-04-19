package com;

//For REST Service
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.AppServer; 

@Path("/Appointments")
public class Appointments {
	
	AppServer Appobj = new AppServer();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAppointments()
	 {
	 return Appobj.readAppointments();
	 } 
}
