package com.vasana.smartattendance.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("student")
	private Student student;

	@SerializedName("professor")
	private Professor professor;

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@SerializedName("message")
	private String message;

	@SerializedName("user")
	private User user;

	@SerializedName("status")
	private boolean status;

	@SerializedName("token")
	private String token;

	public void setStudent(Student student){
		this.student = student;
	}

	public Student getStudent(){
		return student;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return 
			"LoginResponse{" + 
			"student = '" + student + '\'' + 
			",message = '" + message + '\'' + 
			",user = '" + user + '\'' + 
			",status = '" + status + '\'' + 
			",token = '" + token + '\'' + 
			"}";
		}
}