package com.vasana.smartattendance.models;

import com.google.gson.annotations.SerializedName;

public class CreateStudentRequest{

	@SerializedName("userid")
	private String userid;

	public void setUserid(String userid){
		this.userid = userid;
	}

	public String getUserid(){
		return userid;
	}

	@Override
 	public String toString(){
		return 
			"CreateStudentRequest{" + 
			"userid = '" + userid + '\'' + 
			"}";
		}
}