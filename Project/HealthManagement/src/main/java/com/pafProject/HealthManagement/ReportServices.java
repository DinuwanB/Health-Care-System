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

	import model.Report;

	@Path("/Reports")
	public class ReportServices {
		
		Report reportObject = new Report();
				
				@GET
				@Path("/")
				@Produces(MediaType.TEXT_HTML)
				public String readReports()
				{
					return reportObject.readReports();
				}
				
				@POST
				@Path("/")
				@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
				@Produces(MediaType.TEXT_PLAIN)
				public String insertReport(@FormParam("hospital_id")String hospital_id,
						                @FormParam("appid")String appid,
										@FormParam("pat_id") String pat_id,
										@FormParam("DOC_id") String DOC_id,
										@FormParam("Date") String Date,
										@FormParam("Time") String Time,
										@FormParam("Description") String Description)
										
				{
					String output = reportObject.insertReport(hospital_id, appid, pat_id, DOC_id, Date, Time, Description);
					return output;
				
				}

			@PUT 
			@Path("/") 
			@Consumes(MediaType.APPLICATION_JSON) 
			@Produces(MediaType.TEXT_PLAIN) 

				public String updateReport(String reportDetails) 
				{  
				
				//Convert the input string to a JSON object  
				JsonObject Object = new JsonParser().parse(reportDetails).getAsJsonObject(); 
				 
				 //Read the values from the JSON object  
				
						String REP_id = Object.get("REP_id").getAsString();  
						String hospital_id = Object.get("hospital_id").getAsString(); 
						String appid = Object.get("appid").getAsString(); 
						String pat_id = Object.get("pat_id").getAsString();  
						String DOC_id = Object.get("DOC_id").getAsString();  
						String Date = Object.get("Date").getAsString(); 
						String Time = Object.get("Time").getAsString();  
						String Description = Object.get("Description").getAsString(); 
						 
						String output = reportObject.updateReport(REP_id, hospital_id, appid, pat_id, DOC_id, Date, Time, Description);
						 
						return output; 
				
				} 


			@DELETE 
			@Path("/") 
			@Consumes(MediaType.APPLICATION_XML) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String deleteReport(String reportDetails) 
			{  
				//Convert the input string to an XML document  
				Document doc = Jsoup.parse(reportDetails, "", Parser.xmlParser());    
						
				//Read the value from the element <itemID>  
			    String REP_id = doc.select("REP_id").text(); 
						 
			    String output = reportObject.deleteReport(REP_id); 
						 
				return output;
			}

		}










				






