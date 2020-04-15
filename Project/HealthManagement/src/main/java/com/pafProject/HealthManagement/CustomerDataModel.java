package com.pafProject.HealthManagement;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class CustomerDataModel {
	
	Connection con = null;
	
	public CustomerDataModel() {
		String url ="jdbc:mysql://localhost:3306/paf-project?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "root";
		String password = "";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
			System.out.println(" connected");
		}
		catch(Exception e) {
			System.out.println("not connected" + e);
		}
	}
	
	

	public List<Customer> getCustomers() {
		List<Customer> cust = new ArrayList<Customer>();
		
		String sql = "SELECT * FROM customers";
		
		try {
			
			Statement st =  con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Customer cu1 = new Customer();
				
				cu1.setCustomerId(rs.getInt(1));
				cu1.setNIC(rs.getString(2));
				cu1.setFirstname(rs.getString(3));
				cu1.setLastname(rs.getString(4));
				cu1.setEmail(rs.getString(5));
				cu1.setPhoneNumber(rs.getInt(6));
				cu1.setBirthday(rs.getString(7));  
				cu1.setPassword(rs.getString(8));
				
				cust.add(cu1);
				
			}
		} catch (Exception e) {
			System.out.println("catch 1 "+e);
		}
		return cust;
	}

	public Customer getCustomer(int id) {
		
		String sqlq = "SELECT * FROM customers WHERE c_id="+id;
		Customer cu1 = new Customer();
		try {
			Statement st =  con.createStatement();
			ResultSet rs = st.executeQuery(sqlq);
			
			if(rs.next()) {
				
				cu1.setCustomerId(rs.getInt(1));
				cu1.setNIC(rs.getString(2));
				cu1.setFirstname(rs.getString(3));
				cu1.setLastname(rs.getString(4));
				cu1.setEmail(rs.getString(5));
				cu1.setPhoneNumber(rs.getInt(6));
				cu1.setBirthday(rs.getString(7));  
				cu1.setPassword(rs.getString(8));
				
				
			}
		} catch (Exception e) 
		{
			System.out.println("catch 2 "+e);
		}
		
		return cu1;
	}

	public void createUser(Customer cus1) {
		String sql = "INSERT INTO customers VALUES (?,?,?,?,?,?,?,?)";
		try {
			
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, cus1.getCustomerId());
			st.setString(2, cus1.getNIC());
			st.setString(3, cus1.getFirstname());
			st.setString(4, cus1.getLastname());
			st.setString(5, cus1.getEmail());
			st.setInt(6, cus1.getPhoneNumber());
			st.setString(7, cus1.getBirthday());
			st.setString(8, cus1.getPassword());
			
			st.execute();
			
		}catch(Exception e){
			
			System.out.println("catch 3 "+e);
			
		}
		
	}
	
	public void updateUser(Customer cus1) {
		String sql = "UPDATE customers set c_nic=? , FirstName=?, LastName=?, Email=?, PhoneNumber=?, Birthday=?, Password=? WHERE c_id=?";
		try {
			
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, cus1.getNIC());
			st.setString(2, cus1.getFirstname());
			st.setString(3, cus1.getLastname());
			st.setString(4, cus1.getEmail());
			st.setInt(5, cus1.getPhoneNumber());
			st.setString(6, cus1.getBirthday());
			st.setString(7, cus1.getPassword());
			st.setInt(8, cus1.getCustomerId());
			
			st.executeUpdate();
			
		}catch(Exception e){
			
			System.out.println("catch 3 "+e);
			
		}
		
	}



	public void delete(int id) {
		String sql = "DELETE FROM customers WHERE c_id=?";
		try {
			
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			st.executeUpdate();
			
		} catch(Exception e) {
			
			System.out.println("catch 4 "+e);
			
		}
		
		
	}

}
                        