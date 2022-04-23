package com.vasana.smartattendance.uitl.storage.impl;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.vasana.smartattendance.uitl.storage.GlobalConstants;
import com.vasana.smartattendance.uitl.storage.base.KRedis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import timber.log.Timber;

public class KRedisImpl implements KRedis {

    private SharedPreferences mStore;

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
    public String getUserId() {
        return mStore.getString(GlobalConstants.RedisConstants.USER_ID, "");
    }

    @Override
    public void setUserId(String accessToken) {
        storeItem(GlobalConstants.RedisConstants.USER_ID, accessToken);
    }

    @Override
    public String getFcmAccessToken() {
        return mStore.getString(GlobalConstants.RedisConstants.FCM_META, "");
    }

    @Override
    public void setFCmAccessToken(String fCmAccessToken) {
        storeItem(GlobalConstants.RedisConstants.FCM_META, fCmAccessToken);
    }

    @Override
    public String getWeatherAccessToken() {
        Log.e("redis get", "kk"+mStore.getString(GlobalConstants.RedisConstants.WEATHER_META, ""));
        return mStore.getString(GlobalConstants.RedisConstants.WEATHER_META, "");
    }

    @Override
    public void setWeatherAccessToken(String fCmAccessToken) {
        storeItem(GlobalConstants.RedisConstants.WEATHER_META, fCmAccessToken);
        Log.e("redis", "kk"+fCmAccessToken);
    }



}
