package com.growd25.weather.data

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.growd25.weather.entities.City
import com.growd25.weather.entities.CityResult
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherAssetManager @Inject constructor(
    private val context: Context,
    private val gson: Gson
) {

    fun getListCities(): CityResult {
        return try {
            val inputStream = context.assets.open(FILE_NAME)
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val result = gson.fromJson<ArrayList<City>>(
                jsonString,
                object : TypeToken<ArrayList<City>>() {}.type
            )
            CityResult(result, null)
        } catch (e: Exception) {
            Log.e(LOG_TAG, e.toString())
            CityResult(emptyList(), e)

        }
    }

    companion object {

        const val FILE_NAME = "cities.json"
        const val LOG_TAG = "WeatherAssetManager"
    }

}