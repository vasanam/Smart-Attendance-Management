package com.vasana.smartattendance.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Professor{

	@SerializedName("classes")
	private List<ClassesItem> classes;

	@SerializedName("__v")
	private int V;

	@SerializedName("_id")
	private String id;

	@SerializedName("userid")
	private String userid;

	public void setClasses(List<ClassesItem> classes){
		this.classes = classes;
	}

	public List<ClassesItem> getClasses(){
		return classes;
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

	public void setUserid(String userid){
		this.userid = userid;
	}

	public String getUserid(){
		return userid;
	}

	@Override
 	public String toString(){
		return 
			"Professor{" + 
			"classes = '" + classes + '\'' + 
			",__v = '" + V + '\'' + 
			",_id = '" + id + '\'' + 
			",userid = '" + userid + '\'' + 
			"}";
		}
}