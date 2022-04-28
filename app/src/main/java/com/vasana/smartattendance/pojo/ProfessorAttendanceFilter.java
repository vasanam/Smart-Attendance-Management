package com.vasana.smartattendance.pojo;

import com.google.gson.annotations.SerializedName;

public class ProfessorAttendanceFilter{

	@SerializedName("date")
	private String date;

	@SerializedName("classid")
	private String classid;

	@SerializedName("subjectid")
	private String subjectid;

	public String getDate(){
		return date;
	}

	public String getClassid(){
		return classid;
	}

	public String getSubjectid(){
		return subjectid;
	}

	public ProfessorAttendanceFilter(String date, String classid, String subjectid) {
		this.date = date;
		this.classid = classid;
		this.subjectid = subjectid;
	}
}