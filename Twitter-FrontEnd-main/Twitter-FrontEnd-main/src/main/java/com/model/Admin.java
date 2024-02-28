package com.model;

public class Admin {
     private int admin_id;
     private String admin_username;
     private String admin_email;
     private String admin_password;
    
     
     
     
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Admin(String admin_username, String admin_email, String admin_password) {
		super();
		this.admin_username = admin_username;
		this.admin_email = admin_email;
		this.admin_password = admin_password;
	}




	public Admin(int admin_id, String admin_username, String admin_email, String admin_password) {
		super();
		this.admin_id = admin_id;
		this.admin_username = admin_username;
		this.admin_email = admin_email;
		this.admin_password = admin_password;
	}




	public int getAdmin_id() {
		return admin_id;
	}




	public String getAdmin_username() {
		return admin_username;
	}




	public String getAdmin_email() {
		return admin_email;
	}




	public String getAdmin_password() {
		return admin_password;
	}




	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}




	public void setAdmin_username(String admin_username) {
		this.admin_username = admin_username;
	}




	public void setAdmin_email(String admin_email) {
		this.admin_email = admin_email;
	}




	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}




	@Override
	public String toString() {
		return "Admin [admin_id=" + admin_id + ", admin_username=" + admin_username + ", admin_email=" + admin_email
				+ ", admin_password=" + admin_password + "]";
	}
	
}