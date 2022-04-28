package com.vasana.smartattendance.models;

import com.google.gson.annotations.SerializedName;

public class PostAttendanceRequest{

	@SerializedName("studentId")
	private String studentId;

	@SerializedName("classid")
	private String classid;

	@SerializedName("subjectid")
	private String subjectid;

	public PostAttendanceRequest(String studentId, String classid, String subjectid) {
		this.studentId = studentId;
		this.classid = classid;
		this.subjectid = subjectid;
	}

	public String getStudentId(){
		return studentId;
	}

	public String getClassid(){
		return classid;
	}

	public String getSubjectid(){
		return subjectid;
	}
}