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

@Path("/administrators")
public class AdminController {
	AdminModel model = new AdminModel();
	Map<String, Object> data = new HashMap<String, Object>();
	Gson gson = new Gson();

	//INSERT
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertAdmin(String json) {
		Administrators a = gson.fromJson(json, Administrators.class);
		return gson.toJson(model.insertAdmin(a));
	}
	
	//UPDATE
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateAdmin(String json) {
		Administrators a = gson.fromJson(json, Administrators.class);
		return gson.toJson(model.updateAdmin(a));
	}
	
	//SEARCH
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAdmin(@PathParam("id") int id) {
		return gson.toJson(model.searchAdmin(id));
	}
	
	//SEARCH All
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAdmins() {
		return gson.toJson(model.getAllAdmins());
	}
	
	//DELETE
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteAdmin(@PathParam("id") int id) {
		return gson.toJson(model.deleteAdmin(id));
	}



}
