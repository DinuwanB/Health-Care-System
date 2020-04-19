package com;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("/pharmacist")
public class PharmacistController {
	PharmacistModel model = new PharmacistModel();
	Map<String, Object> data = new HashMap<String, Object>();
	Gson gson = new Gson();

	//INSERT
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertPatient(String json) {
		Pharmacist p = gson.fromJson(json, Pharmacist.class);
		return gson.toJson(model.insertPharmacist(p));
	}
	
	//UPDATE
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String updatePatient(String json) {
		Pharmacist p = gson.fromJson(json, Pharmacist.class);
		return gson.toJson(model.updatePharmacist(p));
	}
	
	//DELETE
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deletePatient(@PathParam("id") int id) {
		return gson.toJson(model.deletePharmacist(id));
	}


	//SEARCH
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPatient(@PathParam("id") int id) {
		return gson.toJson(model.searchPharmacist(id));
	}
	
	//SEARCH
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getPatients() {
		return gson.toJson(model.searchAllPharmacist());
	}

}
