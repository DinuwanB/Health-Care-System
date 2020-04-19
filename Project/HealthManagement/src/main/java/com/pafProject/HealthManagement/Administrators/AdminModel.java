package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AdminModel {
	private Connection connect() {
		Connection con = null;
		String url = "jdbc:mysql://127.0.0.1:3306/test";
		String username = "root";
		String password = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
	
	//Checking for the Administrator ID-----------------------------------------------------------------------------------
	
	public boolean checkAvailabilityById(int id) {
		Connection con = connect();
		String query = "select * from administrators where id='" + id + "'";
		int count = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				count++;
			}

			rs.close();
			stmt.close();
			con.close();

			if (count > 0)
				return true;
			else
				return false;

		} catch (Exception e) {
			return true; // If any error then main function will handle it
		}
	}
	
	// Inserting Administrator information----------------------------------------------------------------------------------
	
	public Map<String, Object> insertAdmin(Administrators a) {
		Messages em = new Messages();
		Map<String, Object> data = new HashMap<String, Object>();
		
		Connection con = connect();
		if (con == null) {
			em.setError_code(1);
			em.setError_message("Database Connection Error");
			data.put("Error", em);
			return data;
		}
		
		String query = "insert into administrators(name,email,phone,department) values(?,?,?,?)";

		try {
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, a.getName());
			preparedStmt.setString(2, a.getEmail());
			preparedStmt.setInt(3, a.getPhone());
			preparedStmt.setString(4, a.getDepartment());
			preparedStmt.executeUpdate();
			con.close();

			em.setError_code(0);
			em.setError_message("Administrator Added Successfully !");
			data.put("Successful", em);
			return data;

		}catch (Exception ex) {
			System.out.println(ex);
			em.setError_code(1);
			em.setError_message("Some error occured");
			data.put("Error", em);
			return data;
		}
	}
	
	// Updating Administrator Information ----------------------------------------------------------------------------------
	
	public Map<String, Object> updateAdmin(Administrators a) {
		Messages em = new Messages();
		Map<String, Object> data = new HashMap<String, Object>();
		
		if (checkAvailabilityById(a.getId()) == false) {
			em.setError_code(1);
			em.setError_message("Administartor not found !");
			data.put("Error", em);
			return data;
		}
		
		Connection con = connect();
		if (con == null) {
			em.setError_code(1);
			em.setError_message("Database Connection Error");
			data.put("Error", em);
			return data;
		}
		
		String query = "update administrators set name=?,email=?,phone=?,department=? where id=? ";

		try {
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, a.getName());
			preparedStmt.setString(2, a.getEmail());
			preparedStmt.setInt(3, a.getPhone());
			preparedStmt.setString(4, a.getDepartment());
			preparedStmt.setInt(5, a.getId());
			preparedStmt.executeUpdate();
			con.close();

			em.setError_code(0);
			em.setError_message("Administrator updated successfuly !");
			data.put("Successful", em);
			return data;

		}catch (Exception ex) {
			System.out.println(ex);
			em.setError_code(1);
			em.setError_message("Some error occured");
			data.put("Error", em);
			return data;
		}
	}
	
	
	
	// Get Data of Specific Administrator -------------------------------------------------------------
	
	public Map<String, Object> searchAdmin(int id) {
		Messages em = new Messages();
		Map<String, Object> data = new HashMap<String, Object>();
		Connection con = connect();
		if (con == null) {
			em.setError_code(1);
			em.setError_message("Database Connection Error");
			data.put("Error", em);
			return data;
		}
		
		String query = "select * from administrators where id="+ id;
		Administrators p = new Administrators();
		int count = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			

			while (rs.next()) {
				count++;
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setEmail(rs.getString(3));
				p.setPhone(rs.getInt(4));
				p.setDepartment(rs.getString(5));
			}
			con.close();

			em.setError_code(0);
			em.setError_message("Administrator Data Recieved");
			data.put("Successful", em);
			data.put("NoOfData", count);
			if (count > 0) {
				data.put("Data", p);
			}
			return data;

		}catch (Exception ex) {
			System.out.println(ex);
			em.setError_code(1);
			em.setError_message("Some error occured");
			data.put("Error", em);
			return data;
		}
	}
	
	// Get all Administrators Data ----------------------------------------------------------------------------
	
	public Map<String, Object> getAllAdmins() {
		Messages em = new Messages();
		Map<String, Object> data = new HashMap<String, Object>();
		Connection con = connect();
		if (con == null) {
			em.setError_code(1);
			em.setError_message("Database Connection Error");
			data.put("Error", em);
			return data;
		}
		
		String query = "select * from administrators";
		List<Administrators> administrators = new ArrayList<>();
		int count = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			

			while (rs.next()) {
				count++;
				Administrators p = new Administrators();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setEmail(rs.getString(3));
				p.setPhone(rs.getInt(4));
				p.setDepartment(rs.getString(5));
				administrators.add(p);
			}
			con.close();

			em.setError_code(0);
			em.setError_message("Revieved Data of All the Administrators");
			data.put("Successful", em);
			data.put("NoOfData", count);
			if (count > 0) {
				data.put("Data", administrators);
			}
			return data;

		}catch (Exception ex) {
			System.out.println(ex);
			em.setError_code(1);
			em.setError_message("Some error occured");
			data.put("Error", em);
			return data;
		}
	}
	
	// Delete Specific Administrator ----------------------------------------------------------------------------------------
	
		public Map<String, Object> deleteAdmin(int id) {
			Messages em = new Messages();
			Map<String, Object> data = new HashMap<String, Object>();
			
			if (checkAvailabilityById(id) == false) {
				em.setError_code(1);
				em.setError_message("Data not available");
				data.put("Error", em);
				return data;
			}
			
			Connection con = connect();
			if (con == null) {
				em.setError_code(1);
				em.setError_message("Database Connection Error");
				data.put("Error", em);
				return data;
			}
			
			String query = "delete from administrators where id=?";

			try {
				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt(1, id);
				preparedStmt.executeUpdate();
				con.close();

				em.setError_code(0);
				em.setError_message("Administrator deleted successfully");
				data.put("Successfull", em);
				return data;

			}catch (Exception ex) {
				System.out.println(ex);
				em.setError_code(1);
				em.setError_message("Some error occured");
				data.put("Error", em);
				return data;
			}
		}

}
