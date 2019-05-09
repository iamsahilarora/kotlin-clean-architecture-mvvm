package com.sa.kotlin_cleanarch.sample.model.remote

import com.google.gson.Gson
import java.util.*


/* Created by Sahil Bharti on 5/4/19.
 *
*/
class ReflectionUtil(private val gson: Gson) {


    fun convertPojoToMap(model: Any): java.util.HashMap<String, String> {
        val json = gson.toJson(model)
        return gson.fromJson<java.util.HashMap<String, String>>(json, HashMap::class.java)
    }


}