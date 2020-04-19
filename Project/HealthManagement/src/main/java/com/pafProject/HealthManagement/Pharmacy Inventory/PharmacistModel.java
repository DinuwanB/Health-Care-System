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


public class PharmacistModel {
	private Connection connect() {
		Connection con = null;
		String url = "jdbc:mysql://127.0.0.1:3306/paf";
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
	
	public boolean checkAvailabilityById(int id) {
		Connection con = connect();
		String query = "select * from pharmacist where id='" + id + "'";
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
	
	// Insert--------------------------------------------------------
	public Map<String, Object> insertPharmacist(Pharmacist p) {
		Error em = new Error();
		Map<String, Object> data = new HashMap<String, Object>();
		
		Connection con = connect();
		if (con == null) {
			em.setError_code(1);
			em.setError_message("Database Connection Error");
			data.put("Error", em);
			return data;
		}
		
		String query = "insert into pharmacist(name,email,Expiredate,companyName,price,quantity) values(?,?,?,?,?,?)";

		try {
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, p.getName());
			preparedStmt.setString(2, p.getEmail());
			preparedStmt.setString(3, p.getDate());
			preparedStmt.setString(4, p.getCompanyName());
			preparedStmt.setFloat(5, p.getPrice());
			preparedStmt.setInt(6, p.getQuantity());
			preparedStmt.executeUpdate();
			con.close();

			em.setError_code(0);
			em.setError_message("Data inserted");
			data.put("Error", em);
			return data;

		}catch (Exception ex) {
			System.out.println(ex);
			em.setError_code(1);
			em.setError_message("Some error occured");
			data.put("Error", em);
			return data;
		}
	}
	
	// Update --------------------------------------------------------
	public Map<String, Object> updatePharmacist(Pharmacist p) {
		Error em = new Error();
		Map<String, Object> data = new HashMap<String, Object>();
		
		if (checkAvailabilityById(p.getId()) == false) {
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
		
		String query = "update pharmacist set name=?,email=?,Expiredate=?,companyName=?,price=?,quantity=? where id=? ";
		
		try {
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, p.getName());
			preparedStmt.setString(2, p.getEmail());
			preparedStmt.setString(3, p.getDate());
			preparedStmt.setString(4, p.getCompanyName());
			preparedStmt.setFloat(5, p.getPrice());
			preparedStmt.setInt(6, p.getQuantity());
			preparedStmt.setInt(7, p.getId());
			preparedStmt.executeUpdate();
			con.close();

			em.setError_code(0);
			em.setError_message("Data updated");
			data.put("Error", em);
			return data;

		}catch (Exception ex) {
			System.out.println(ex);
			em.setError_code(1);
			em.setError_message("Some error occured");
			data.put("Error", em);
			return data;
		}
	}
	
	// Delete --------------------------------------------------------
	public Map<String, Object> deletePharmacist(int id) {
		Error em = new Error();
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
		
		String query = "delete from pharmacist where id=?";

		try {
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, id);
			preparedStmt.executeUpdate();
			con.close();

			em.setError_code(0);
			em.setError_message("Data deleted");
			data.put("Error", em);
			return data;

		}catch (Exception ex) {
			System.out.println(ex);
			em.setError_code(1);
			em.setError_message("Some error occured");
			data.put("Error", em);
			return data;
		}
	}
	
	// Get All Data --------------------------------------------------------
	public Map<String, Object> searchPharmacist(int id) {
		Error em = new Error();
		Map<String, Object> data = new HashMap<String, Object>();
		Connection con = connect();
		if (con == null) {
			em.setError_code(1);
			em.setError_message("Database Connection Error");
			data.put("Error", em);
			return data;
		}
		
		String query = "select * from pharmacist where id="+ id;
		Pharmacist p = new Pharmacist();
		int count = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			

			while (rs.next()) {
				count++;
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setEmail(rs.getString(3));
				p.setDate(rs.getString(4));
				p.setCompanyName(rs.getString(5));
				p.setPrice(rs.getInt(6));
				p.setQuantity(rs.getInt(7));
			}
			con.close();

			em.setError_code(0);
			em.setError_message("Data Recieved");
			data.put("Error", em);
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
	
	// Get all Data --------------------------------------------------------
	public Map<String, Object> searchAllPharmacist() {
		Error em = new Error();
		Map<String, Object> data = new HashMap<String, Object>();
		Connection con = connect();
		if (con == null) {
			em.setError_code(1);
			em.setError_message("Database Connection Error");
			data.put("Error", em);
			return data;
		}
		
		String query = "select * from pharmacist";
		List<Pharmacist> pharmacists = new ArrayList<>();
		int count = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			

			while (rs.next()) {
				count++;
				Pharmacist p = new Pharmacist();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setEmail(rs.getString(3));
				p.setDate(rs.getString(4));
				p.setCompanyName(rs.getString(5));
				p.setPrice(rs.getInt(6));
				p.setQuantity(rs.getInt(7));
				pharmacists.add(p);
			}
			con.close();

			em.setError_code(0);
			em.setError_message("Data Recieved");
			data.put("Error", em);
			data.put("NoOfData", count);
			if (count > 0) {
				data.put("Data", pharmacists);
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

}
