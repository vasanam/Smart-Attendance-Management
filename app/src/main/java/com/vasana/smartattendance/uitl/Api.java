package com.vasana.smartattendance.uitl;


import com.vasana.smartattendance.models.AttendanceResponse;
import com.vasana.smartattendance.models.AttendanceResponseItem;
import com.vasana.smartattendance.models.BaseResponse;
import com.vasana.smartattendance.models.LoginRequest;
import com.vasana.smartattendance.models.LoginResponse;
import com.vasana.smartattendance.models.PostAttendanceRequest;
import com.vasana.smartattendance.models.PostAttendanceResponse;
import com.vasana.smartattendance.models.Professor;
import com.vasana.smartattendance.models.Student;
import com.vasana.smartattendance.models.UpdateUserDetails;
import com.vasana.smartattendance.models.User;
import com.vasana.smartattendance.pojo.FillAttendanceRequest;
import com.vasana.smartattendance.pojo.ProfessorAttendanceFilter;
import com.vasana.smartattendance.pojo.StudentAttendanceFilter;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    @POST("/users/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @GET("users/student/{id}")
    Call<Student> getStudent(@Path("id") String userId);

    @GET("users/prof/{id}")
    Call<Professor> getProfessor(@Path("id") String userId);

    @POST("attendance")
    Call<PostAttendanceResponse> postAttendance(@Body PostAttendanceRequest request);

    @POST("attendance/fill")
    Call<BaseResponse> fill(@Body FillAttendanceRequest fillAttendanceRequest);

    @PUT("users/{id}")
    Call<User> updateUser(@Path("id") String id, @Body UpdateUserDetails userDetails);

    @POST("attendance/filter")
    Call<List<AttendanceResponseItem>> fetchAttendance(@Body StudentAttendanceFilter filter);

    @POST("attendance/filter")
    Call<List<AttendanceResponseItem>> fetchAttendance(@Body ProfessorAttendanceFilter filter);

}
