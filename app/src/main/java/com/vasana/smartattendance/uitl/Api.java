package com.vasana.smartattendance.uitl;


import com.vasana.smartattendance.models.LoginRequest;
import com.vasana.smartattendance.models.LoginResponse;
import com.vasana.smartattendance.models.Professor;
import com.vasana.smartattendance.models.Student;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    @POST("/users/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @GET("users/student/{id}")
    Call<Student> getStudent(@Path("id") String userId);

    @GET("users/prof/{id}")
    Call<Professor> getProfessor(@Path("id") String userId);

}
