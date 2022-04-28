package com.vasana.smartattendance.models;

import com.google.gson.annotations.SerializedName;

public class AttendanceFillRequest{

	@SerializedName("classid")
	private String classid;

	@SerializedName("barcodegeneratedat")
	private String barcodegeneratedat;

	@SerializedName("expiry_time")
	private String expiryTime;

	@SerializedName("subjectid")
	private String subjectid;

	public AttendanceFillRequest(String classid, String barcodegeneratedat, String expiryTime, String subjectid) {
		this.classid = classid;
		this.barcodegeneratedat = barcodegeneratedat;
		this.expiryTime = expiryTime;
		this.subjectid = subjectid;
	}

	public String getClassid(){
		return classid;
	}

	public String getBarcodegeneratedat(){
		return barcodegeneratedat;
	}

	public String getExpiryTime(){
		return expiryTime;
	}

	public String getSubjectid(){
		return subjectid;
	}
}