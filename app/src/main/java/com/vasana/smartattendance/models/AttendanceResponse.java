package com.vasana.smartattendance.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AttendanceResponse{

	@SerializedName("AttendanceResponse")
	private List<AttendanceResponseItem> attendanceResponse;

	public List<AttendanceResponseItem> getAttendanceResponse(){
		return attendanceResponse;
	}
}