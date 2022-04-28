package com.vasana.smartattendance.uitl.storage.base;

import com.vasana.smartattendance.models.Professor;
import com.vasana.smartattendance.models.Student;
import com.vasana.smartattendance.models.User;

import org.json.JSONArray;
import org.json.JSONObject;

public interface KRedis {

    void storeItem(String key, String value);

    void storeItem(String key, Integer value);

    void storeItem(String key, Boolean value);

    void storeItem(String key, Double value);


    void storeItem(String key, Long value);


    void storeItem(String key, Float value);


    void storeItem(String key, JSONObject value);


    void storeItem(String key, JSONArray value);


    String retrieveString(String key, String defaultValue);


    Integer retrieveInteger(String key, Integer defaultValue);


    Double retrieveDouble(String key, Double defaultValue);


    Boolean retrieveBoolean(String key, Boolean defaultValue);

    Float retrieveFloat(String key, Float defaultValue);


    Long retrieveLong(String key, Long defaultValue);


    JSONObject retrieveJSONObject(String key, JSONObject defaultValue);


    JSONArray retrieveJSONArray(String key, JSONArray defaultValue);


    void deleteItem(String key);


    void deleteAll();

    String getAccessToken();

    void setUserId(String accessToken);

    String getUserId();

    void setAccessToken(String accessToken);



    void setUser(Student student);

    void setProfessor(Professor professor);

    Student getStudent();

    Professor getProfessor();

}