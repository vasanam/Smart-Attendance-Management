package com.vasana.smartattendance.uitl.storage

object GlobalConstants {

    object RedisConstants {
        const val USER_NAME = "user_name"
        const val USER_ID = "USER_ID"
        const val USER_NUMBER = "user_number"
        const val REFRESH_TOKEN = "REFRESH_TOKEN"
        const val IS_LOGGED_IN = "IS_LOGGED_IN"
        const val IS_STUDENT = "IS_STUDENT"
        const val STUDENT_ID = "STUDENT_ID"
        const val PROFESSOR_ID = "PROFESSOR_ID"
        const val IS_INTRO_DONE = "IS_INTRO_DONE"
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
        const val LANGUAGE_SELECTION = "LANGUAGE_SELECTION"
        const val FOLLOWER_COUNT = "FOLLOWER_COUNT"
        const val MYPOSTCOUNT = "MYPOSTCOUNT"
        const val FOLLOWING_COUNT = "FOLLOWING_COUNT"
        const val CART_COUNT = "CART_COUNT"
        const val FOFCM = "FOFCM"
        const val FCM = "FCM"
        const val FCM_META = "FCM_META"
        const val WEATHER_META = "WEATHER_META"
    }

    object Temp {
        const val DOMAIN = "https://api.eraithu.in/Eraithu/"
        const val GALLERY_PATH = "$DOMAIN/pub/media/catalog/product"
    }

}