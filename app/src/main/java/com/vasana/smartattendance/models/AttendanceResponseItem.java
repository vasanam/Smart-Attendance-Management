package com.vasana.smartattendance.models;

import com.google.gson.annotations.SerializedName;

public class AttendanceResponseItem {

    @SerializedName("date")
    private String date;

    @SerializedName("studentId")
    private StudentAbr student;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("classid")
    private String classid;

    @SerializedName("__v")
    private int V;

    @SerializedName("_id")
    private String id;

    @SerializedName("barcodegeneratedat")
    private String barcodegeneratedat;

    @SerializedName("expiry_time")
    private String expiryTime;

    @SerializedName("subjectid")
    private String subjectid;

    @SerializedName("status")
    private String status;

    @SerializedName("updatedAt")
    private String updatedAt;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public StudentAbr getStudent() {
        return student;
    }

    public void setStudent(StudentAbr student) {
        this.student = student;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public int getV() {
        return V;
    }

    public void setV(int v) {
        V = v;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBarcodegeneratedat() {
        return barcodegeneratedat;
    }

    public void setBarcodegeneratedat(String barcodegeneratedat) {
        this.barcodegeneratedat = barcodegeneratedat;
    }

    public String getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}