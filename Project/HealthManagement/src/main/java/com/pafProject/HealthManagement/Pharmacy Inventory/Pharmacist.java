package com;

public class Pharmacist {
	private int Id;
	private String Name;
	private String Email;
	private String Expiredate;
	private String CompanyName;
	private float Price;
	private int Quantity;
 
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		this.Email = email;
	}
	
	public String getDate() {
		return Expiredate;
	}

	public void setDate(String date) {
		this.Expiredate = date;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		this.CompanyName = companyName;
	}

	public float getPrice() {
		return Price;
	}

	public void setPrice(float price) {
		this.Price = price;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
}
