package com.vasana.smartattendance.models;

import com.google.gson.annotations.SerializedName;

public class LoginRequest{

	@SerializedName("password")
	private String password;

	@SerializedName("username")
	private String username;

	public LoginRequest(String password, String username) {
		this.password = password;
		this.username = username;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"LoginRequest{" + 
			"password = '" + password + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}