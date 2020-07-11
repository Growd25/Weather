package com.growd25.weather.data

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.growd25.weather.entities.City
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherAssetManager @Inject constructor(
    private val context: Context,
    private val gson: Gson
) {

    init {
        Log.i("den","WeatherMANAGER!!!")
    }


    fun getListCities(): List<City> {
        return try {
            val inputStream = context.assets.open(JSON_FILE)
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            gson.fromJson(jsonString, object : TypeToken<ArrayList<City>>() {}.type)
        } catch (e: Exception) {
            Log.e(LOG_TAG, e.toString())
            emptyList()
        }
    }

    companion object {
        const val JSON_FILE = "cities.json"
        const val LOG_TAG = "WeatherAssetManager"
    }

}