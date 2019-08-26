package com.sa.kotlin_cleanarch.sample.model.local.preference

import android.content.SharedPreferences


/* Created by Sahil Bharti on 10/1/19.
 *
*/

class PreferenceHelper constructor(private val mSharedPreferences: SharedPreferences) {


    fun put(key: String, value: String) {
        mSharedPreferences.edit().putString(key, value).apply()
    }

    fun put(key: String, value: Int) {
        mSharedPreferences.edit().putInt(key, value).apply()
    }

    fun put(key: String, value: Long) {
        mSharedPreferences.edit().putLong(key, value).apply()
    }

    fun put(key: String, value: Float) {
        mSharedPreferences.edit().putFloat(key, value).apply()
    }

    fun put(key: String, value: Boolean) {
        mSharedPreferences.edit().putBoolean(key, value).apply()
    }

    operator fun get(key: String): String {
        return mSharedPreferences.getString(key, "")?:""
    }

    operator fun get(key: String, defaultValue: Int): Int? {
        return mSharedPreferences.getInt(key, defaultValue)
    }

    operator fun get(key: String, defaultValue: Long): Long? {
        return mSharedPreferences.getLong(key, defaultValue)
    }

    operator fun get(key: String, defaultValue: Float): Float? {
        return mSharedPreferences.getFloat(key, defaultValue)
    }

    fun getBoolean(key: String): Boolean {
        return mSharedPreferences.getBoolean(key, false)
    }

    fun deleteSavedData(key: String) {
        mSharedPreferences.edit().remove(key).apply()
    }



}