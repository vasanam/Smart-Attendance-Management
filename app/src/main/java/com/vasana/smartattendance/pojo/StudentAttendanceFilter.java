package com.vasana.smartattendance.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StudentAttendanceFilter implements Serializable {

	@SerializedName("studentId")
	private String studentId;

	public String getStudentId(){
		return studentId;
	}

	public StudentAttendanceFilter(String studentId) {
		this.studentId = studentId;
	}
}