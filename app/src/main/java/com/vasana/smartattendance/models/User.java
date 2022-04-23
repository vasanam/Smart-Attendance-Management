package com.vasana.smartattendance.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User  implements Serializable {

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("password")
	private String password;

	@SerializedName("__v")
	private int V;

	@SerializedName("_id")
	private String id;

	@SerializedName("type")
	private int type;

	@SerializedName("username")
	private String username;

	@SerializedName("updatedAt")
	private String updatedAt;

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setV(int V){
		this.V = V;
	}

	public int getV(){
		return V;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setType(int type){
		this.type = type;
	}

	public int getType(){
		return type;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"createdAt = '" + createdAt + '\'' + 
			",password = '" + password + '\'' + 
			",__v = '" + V + '\'' + 
			",_id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			",username = '" + username + '\'' + 
			",updatedAt = '" + updatedAt + '\'' + 
			"}";
		}
}