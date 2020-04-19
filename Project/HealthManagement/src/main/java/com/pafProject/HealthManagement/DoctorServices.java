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

import model.Doctor;

@Path("/Doctors")
public class DoctorServices {
	
	Doctor doctorObject = new Doctor();
			
			@GET
			@Path("/")
			@Produces(MediaType.TEXT_HTML)
			public String readDoctors()
			{
				return doctorObject.readDoctors();
			}
			
			@POST
			@Path("/")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces(MediaType.TEXT_PLAIN)
			public String insertDoctor(@FormParam("firstname")String firstname,
					                @FormParam("lastname")String lastname,
									@FormParam("age") String age,
									@FormParam("gender") String gender,
									@FormParam("email") String email,
									@FormParam("phonenumber") String phonenumber,
									@FormParam("address") String address,
									@FormParam("specialization") String specialization,
									@FormParam("password") String password)
			{
				String output = doctorObject.insertDoctor(firstname, lastname, age, gender, email, phonenumber, address, specialization, password);
				return output;
			
			}

		@PUT 
		@Path("/") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 

			public String updateDoctor(String doctorDetails) 
			{  
			
			//Convert the input string to a JSON object  
			JsonObject Object = new JsonParser().parse(doctorDetails).getAsJsonObject(); 
			 
			 //Read the values from the JSON object  
			
					String DOC_id = Object.get("DOC_id").getAsString();  
					String firstname = Object.get("firstname").getAsString(); 
					String lastname = Object.get("lastname").getAsString(); 
					String age = Object.get("age").getAsString();  
					String gender = Object.get("gender").getAsString();  
					String email = Object.get("email").getAsString(); 
					String phonenumber = Object.get("phonenumber").getAsString();  
					String address = Object.get("address").getAsString();  
					String specialization = Object.get("specialization").getAsString();  
					String password = Object.get("password").getAsString(); 
					 
					String output = doctorObject.updateDoctor(DOC_id, firstname, lastname, age, gender, email, phonenumber, address, specialization, password);
					 
					return output; 
			
			} 


		@DELETE 
		@Path("/") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String deleteDoctor(String doctorDetails) 
		{  
			//Convert the input string to an XML document  
			Document doc = Jsoup.parse(doctorDetails, "", Parser.xmlParser());    
					
			//Read the value from the element <itemID>  
		    String DOC_id = doc.select("DOC_id").text(); 
					 
		    String output = doctorObject.deleteDoctor(DOC_id); 
					 
			return output;
		}

	}










			
			