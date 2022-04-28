package com.vasana.smartattendance.uitl.storage.impl;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.vasana.smartattendance.models.Professor;
import com.vasana.smartattendance.models.Student;
import com.vasana.smartattendance.uitl.storage.GlobalConstants;
import com.vasana.smartattendance.uitl.storage.base.KRedis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import timber.log.Timber;

public class KRedisImpl implements KRedis {

    private SharedPreferences mStore;
    private Gson gson = new Gson();


    public KRedisImpl(SharedPreferences mSharedPreferences) {
        this.mStore = mSharedPreferences;
    }

    @Override
    public void storeItem(String key, String value) {
//        Timber.e("key : " + key + " , value : " + value);
        mStore.edit().putString(key, value).apply();
    }

    @Override
    public void storeItem(String key, Integer value) {
        mStore.edit().putInt(key, value).apply();
//        Timber.e("key : " + key + " , value : " + value);
    }

    @Override
    public void storeItem(String key, Boolean value) {
        mStore.edit().putBoolean(key, value).apply();
        Timber.e("key : " + key + " , value : " + value);
    }

    @Override
    public void storeItem(String key, Double value) {
        storeItem(key, value.toString());
    }

    @Override
    public void storeItem(String key, Long value) {
        mStore.edit().putLong(key, value).apply();
        Timber.e("key : " + key + " , value : " + value);
    }

    @Override
    public void storeItem(String key, Float value) {
        mStore.edit().putFloat(key, value).apply();
        Timber.e("key : " + key + " , value : " + value);
    }

    @Override
    public void storeItem(String key, JSONObject value) {
        storeItem(key, value.toString());
    }

    @Override
    public void storeItem(String key, JSONArray value) {
        storeItem(key, value.toString());
    }

    @Override
    public String retrieveString(@NonNull String key, @NonNull String defaultValue) {
        if (mStore != null)
            if (mStore.contains(key))
                return mStore.getString(key, defaultValue);
        return defaultValue;
    }

    @Override
    public Integer retrieveInteger(String key, Integer defaultValue) {
        if (mStore != null)
            if (mStore.contains(key))
                return mStore.getInt(key, defaultValue);
        return defaultValue;
    }

    @Override
    public Double retrieveDouble(String key, Double defaultValue) {
        if (mStore != null)
            if (mStore.contains(key))
                return Double.valueOf(retrieveString(key, defaultValue.toString()));
        return defaultValue;
    }

    @Override
    public Boolean retrieveBoolean(String key, Boolean defaultValue) {
        if (mStore != null)
            if (mStore.contains(key))
                return mStore.getBoolean(key, defaultValue);
        return defaultValue;
    }

    @Override
    public Float retrieveFloat(String key, Float defaultValue) {
        if (mStore != null)
            if (mStore.contains(key))
                return mStore.getFloat(key, defaultValue);
        return defaultValue;
    }

    @Override
    public Long retrieveLong(String key, Long defaultValue) {
        if (mStore != null)
            if (mStore.contains(key))
                return mStore.getLong(key, defaultValue);
        return defaultValue;
    }

    @Override
    public JSONObject retrieveJSONObject(String key, JSONObject defaultValue) {
        if (mStore != null)
            if (mStore.contains(key)) {
                try {
                    return new JSONObject(retrieveString(key, defaultValue.toString()));
                } catch (JSONException e) {
                    return defaultValue;
                }
            }
        return defaultValue;
    }

    @Override
    public JSONArray retrieveJSONArray(String key, JSONArray defaultValue) {
        if (mStore != null)
            if (mStore.contains(key)) {
                try {
                    return new JSONArray(retrieveString(key, defaultValue.toString()));
                } catch (JSONException e) {
                    return defaultValue;
                }
            }
        return defaultValue;
    }

    @Override
    public void deleteItem(String key) {
        mStore.edit().remove(key).apply();

    }

    @Override
    public void deleteAll() {
        mStore.edit().clear().apply();
    }

    @Override
    public String getAccessToken() {
        return mStore.getString(GlobalConstants.RedisConstants.ACCESS_TOKEN, "");
    }

    @Override
    public void setAccessToken(String accessToken) {
        storeItem(GlobalConstants.RedisConstants.ACCESS_TOKEN, accessToken);
    }

    @Override
    public void setUser(Student student) {
        storeItem("stud", gson.toJson(student));
    }

    @Override
    public void setProfessor(Professor professor) {
        storeItem("prof", gson.toJson(professor));

    }

    @Override
    public Student getStudent() {
        return gson.fromJson(retrieveString("stud", ""), Student.class);
    }

    @Override
    public Professor getProfessor() {
        return gson.fromJson(retrieveString("prof", ""), Professor.class);

    }

    @Override
    public String getUserId() {
        return mStore.getString(GlobalConstants.RedisConstants.USER_ID, "");
    }

    @Override
    public void setUserId(String accessToken) {
        storeItem(GlobalConstants.RedisConstants.USER_ID, accessToken);
    }


}
