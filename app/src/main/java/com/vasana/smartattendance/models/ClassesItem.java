package com.vasana.smartattendance.models;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ClassesItem implements Serializable {

	@SerializedName("subjects")
	private List<SubjectsItem> subjects;

	@SerializedName("__v")
	private int V;

	@SerializedName("name")
	private String name;

	@SerializedName("students")
	private List<Object> students;

	@SerializedName("_id")
	private String id;

	public void setSubjects(List<SubjectsItem> subjects){
		this.subjects = subjects;
	}

	public List<SubjectsItem> getSubjects(){
		return subjects;
	}

	public void setV(int V){
		this.V = V;
	}

	public int getV(){
		return V;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setStudents(List<Object> students){
		this.students = students;
	}

	public List<Object> getStudents(){
		return students;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"ClassesItem{" + 
			"subjects = '" + subjects + '\'' + 
			",__v = '" + V + '\'' + 
			",name = '" + name + '\'' + 
			",students = '" + students + '\'' + 
			",_id = '" + id + '\'' + 
			"}";
		}
}