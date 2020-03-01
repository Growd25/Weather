package com.growd25.weather.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.growd25.weather.data.net.WeatherApi
import com.growd25.weather.entities.City
import com.growd25.weather.entities.model.ExamplePojo
import javax.inject.Inject

class DefaultWeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,
    private val context: Context,
    private val gson: Gson
) : WeatherRepository {

    override suspend fun getListCities(): ArrayList<City> {
        val jsonFile = "cities.json"
        try {
            val inputStream = context.assets.open(jsonFile)
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            return gson.fromJson(jsonString, object : TypeToken<ArrayList<City>>() {}.type)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ArrayList()
    }

    override suspend fun getCityWeatherDetail(cityId: Int): ExamplePojo {
        return weatherApi.searchById(cityId)
    }

}




