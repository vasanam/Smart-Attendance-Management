package com.vasana.smartattendance.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StudentAbr implements Serializable {
	@SerializedName("class")
	private String classesItem;

	public String getClassesItem() {
		return classesItem;
	}

	public void setClassesItem(String classesItem) {
		this.classesItem = classesItem;
	}

	@SerializedName("__v")
	private int V;

	@SerializedName("_id")
	private String id;

	@SerializedName("userid")
	private User userid;


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

	public User getUserid() {
		return userid;
	}

	public void setUserid(User userid) {
		this.userid = userid;
	}

	@Override
 	public String toString(){
		return 
			"Student{" + 
			"__v = '" + V + '\'' + 
			",_id = '" + id + '\'' + 
			",userid = '" + userid + '\'' + 
			"}";
		}
}