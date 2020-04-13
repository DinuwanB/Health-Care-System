package com.pafProject.HealthManagement;

import java.util.List;
import java.sql.*;

public class CustomerDataModel {
	
	Connection con = null;

	public List<Customer> getCustomers() {
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
		return null;
	}

	public Customer getCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

}
                        