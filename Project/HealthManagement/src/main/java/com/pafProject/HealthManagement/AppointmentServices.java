package com;

    //For REST Service
	import javax.ws.rs.Consumes;
	import javax.ws.rs.DELETE;
	import javax.ws.rs.FormParam;
	import javax.ws.rs.GET;
	import javax.ws.rs.POST;
	import javax.ws.rs.PUT;
	import javax.ws.rs.Path;
	import javax.ws.rs.Produces;
	import javax.ws.rs.core.MediaType;

	//For XML
	import org.jsoup.Jsoup;
	import org.jsoup.nodes.Document;
	import org.jsoup.parser.Parser;

	//For JSON
	import com.google.gson.JsonObject;
	import com.google.gson.JsonParser;

	import model.Appointment;

	@Path("/Appointments")
	public class AppointmentServices {
		
		Appointment appointmentObject = new Appointment();
				
				@GET
				@Path("/")
				@Produces(MediaType.TEXT_HTML)
				public String readAppointments()
				{
					return appointmentObject.readAppointments();
				}
				
				@POST
				@Path("/")
				@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
				@Produces(MediaType.TEXT_PLAIN)
				public String insertAppointment(@FormParam("Date")String Date,
						                @FormParam("Time")String Time,
										@FormParam("pat_id") String pat_id,
										@FormParam("hospital_id") String hospital_id,
										@FormParam("DOC_id") String DOC_id)
										
										
				{
					String output = appointmentObject.insertAppointment(Date, Time, pat_id, hospital_id, DOC_id);
					return output;
				
				}

			@PUT 
			@Path("/") 
			@Consumes(MediaType.APPLICATION_JSON) 
			@Produces(MediaType.TEXT_PLAIN) 

				public String updateAppointment(String appointmentDetails) 
				{  
				
				//Convert the input string to a JSON object  
				JsonObject Object = new JsonParser().parse(appointmentDetails).getAsJsonObject(); 
				 
				 //Read the values from the JSON object  
				
						String appid = Object.get("appid").getAsString();  
						String Date = Object.get("Date").getAsString(); 
						String Time = Object.get("Time").getAsString(); 
						String pat_id = Object.get("pat_id").getAsString(); 
					    String hospital_id = Object.get("hospital_id").getAsString();  
						String DOC_id = Object.get("DOC_id").getAsString(); 
						
						 
						String output = appointmentObject.updateAppointment(appid, Date, Time, pat_id, hospital_id, DOC_id);
						 
						return output; 
				
				} 


			@DELETE 
			@Path("/") 
			@Consumes(MediaType.APPLICATION_XML) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String deleteAppointment(String appointmentDetails) 
			{  
				//Convert the input string to an XML document  
				Document doc = Jsoup.parse(appointmentDetails, "", Parser.xmlParser());    
						
				//Read the value from the element <itemID>  
			    String appid = doc.select("appid").text(); 
						 
			    String output = appointmentObject.deleteAppointment(appid); 
						 
				return output;
			}

		}










				



