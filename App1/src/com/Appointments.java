package com;

import java.sql.Date;
import java.sql.Time;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
//For REST Service
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointment(@FormParam("appid") int appid,
	 @FormParam("pat_id") int pat_id,
	 @FormParam("DOC_id") int DOC_id,
	 @FormParam("time") Time time,
	 @FormParam("date") Date date)
	{
	 String output = Appobj.insertAppointment( appid,  pat_id,  DOC_id, time,date);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointment(String AppData)
	{
	//Convert the input string to a JSON object
	 JsonObject AppObject = new JsonParser().parse(AppData).getAsJsonObject();
	//Read the values from the JSON object
	 String appid = AppObject.get("appid").getAsString();
	 String pat_id = AppObject.get("pat_id").getAsString();
	 String DOC_id = AppObject.get("DOC_id").getAsString();
	 String time = AppObject.get("time").getAsString();
	 String date = AppObject.get("date").getAsString();
	 String output = Appobj.updateAppointment(appid, pat_id, DOC_id, time, date);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointment(String AppData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(AppData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String appid = doc.select("appid").text();
	 String output = Appobj.deleteAppointment(appid);
	return output;
	}


}
