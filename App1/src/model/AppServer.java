package model;

import java.sql.*;

public class AppServer {

	
	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "root", "1234");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	public String insertAppointment(int appid, int pat_id, int DOC_id, Time time, Date date)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 
	 // create a prepared statement
	 String query = " insert into App1(`appid`,`pat_id`,`DOC_id`,`time`,`date`)"
	 + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, appid);
	 preparedStmt.setInt(2, pat_id);
	 preparedStmt.setInt(3, DOC_id);
	 preparedStmt.setTime(4, time);
	 preparedStmt.setDate(5, date); 
	
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the Appointment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	public String readAppointments()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr><th>Appointment ID</th><th>Patiant ID</th><th>Doctor ID</th><th>Time</th><th>Date</th><th>Update</th><th>Remove</th></tr>";
	 String query = "select * from App1";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String appid = Integer.toString(rs.getInt("appid"));
	 String pat_id = Integer.toString(rs.getInt("pat_id"));
	 String DOC_id = Integer.toString(rs.getInt("Doc_id"));
	 Time time = rs.getTime("time");
	 Date date = rs.getDate("date");
	 
	 // Add into the html table
	 output += "<tr><td>" + appid + "</td>";
	 output += "<td>" + pat_id + "</td>";
	 output += "<td>" + DOC_id + "</td>";
	 output += "<td>" + time + "</td>";
	 output += "<td>" + date + "</td>";
	 
	 // buttons
	 output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>" + "<td><form method=\"post\" action=\"items.jsp\">" + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"+ "<input name=\"appid\" type=\"hidden\" value=\"" + appid + "\">" + "</form></td></tr>"; 
	 }
	 con.close();
	 
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the Appointment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	public String updateAppointment(String appid, String pat_id, String DOC_id, String time, String date)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 
	 // create a prepared statement
	 String query = "UPDATE App1 SET appid=?,pat_id=?,DOC_id=?,time=?,date=? WHERE appid=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 preparedStmt.setString(1, appid);
	 preparedStmt.setString(2, pat_id);
	 preparedStmt.setString(3, DOC_id);
	 preparedStmt.setString(4, time);
	 preparedStmt.setString(5, date); 
	 
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the Appointment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	public String deleteAppointment(String appid)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 
	 // create a prepared statement
	 String query = "delete from items where appid=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(appid));
	 
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the Appoinment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	}

