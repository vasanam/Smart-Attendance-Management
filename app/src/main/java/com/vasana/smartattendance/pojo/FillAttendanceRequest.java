package com.vasana.smartattendance.pojo;

import com.google.gson.annotations.SerializedName;

public class FillAttendanceRequest{

	@SerializedName("classid")
	private String classid;

	@SerializedName("barcodegeneratedat")
	private String barcodegeneratedat;

	@SerializedName("expiry_time")
	private String expiryTime;

	@SerializedName("subjectid")
	private String subjectid;

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

	public FillAttendanceRequest(String classid, String barcodegeneratedat, String expiryTime, String subjectid) {
		this.classid = classid;
		this.barcodegeneratedat = barcodegeneratedat;
		this.expiryTime = expiryTime;
		this.subjectid = subjectid;
	}
}