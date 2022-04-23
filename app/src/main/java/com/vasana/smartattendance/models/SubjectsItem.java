package com.vasana.smartattendance.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SubjectsItem implements Serializable {

	@SerializedName("__v")
	private int V;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("_id")
	private String id;

	public void setV(int V){
		this.V = V;
	}

	public int getV(){
		return V;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"SubjectsItem{" + 
			"__v = '" + V + '\'' + 
			",name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",_id = '" + id + '\'' + 
			"}";
		}
}