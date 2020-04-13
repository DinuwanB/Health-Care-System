package com.pafProject.HealthManagement;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class CustomerDataModel {
	
	Connection con = null;
	
	public CustomerDataModel()
	{
		
		String url = "jdbc:mysql://localhost:3306/paf-project";
		String username = "root";
		String password = "";
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}

	public List<Customer> getCustomers() {
		List<Customer> cust = new ArrayList<Customer>();
		String sql = "SELECT * FROM customer";
		
		try {
			Statement st =  con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Customer cu1 = new Customer();
				
				cu1.setFirstname(rs.getString(1));
				cu1.setFirstname(rs.getString(2));
				cu1.setLastname(rs.getString(3));
				cu1.setEmail(rs.getString(4));
				cu1.setPhoneNumber(rs.getInt(5));
				cu1.setNIC(rs.getString(6));
				cu1.setBirthday(rs.getString(7));  
				cu1.setPassword(rs.getString(8));
				
				cust.add(cu1);
				
			}
		} catch (Exception e) {
			
		}
		return cust;
	}

	public Customer getCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

}
                        