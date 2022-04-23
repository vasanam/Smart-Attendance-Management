package com.vasana.smartattendance.uitl.storage;

import android.content.Context;

import com.vasana.smartattendance.BuildConfig;
import com.vasana.smartattendance.uitl.storage.base.KRedis;
import com.vasana.smartattendance.uitl.storage.impl.KRedisImpl;


public class RedisProvider {
    private static KRedis redis;
    private static String SHARED_PREF_NAME = BuildConfig.APPLICATION_ID + "_prefs";

    public static KRedis getRedis(Context context) {
        if (redis == null) {
            redis = new KRedisImpl(context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE));
        }
        return redis;
    }
}
