package com.example.weather.repository

import android.content.Context
import android.content.Intent
import com.example.weather.App
import com.example.weather.data.City
import com.example.weather.data.model.ExamplePojo
import com.example.weather.data.net.WeatherApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.Exception
import javax.inject.Inject

class DefaultWeatherRepository @Inject constructor() : WeatherRepository {
    @Inject lateinit var weatherApi: WeatherApi
    @Inject lateinit var context: Context
    @Inject lateinit var gson: Gson


    init {
        App.component.inject(this)
    }

    override suspend fun getListCities(): ArrayList<City> {
        try {
            val inputStream = context.assets.open("cities.json")
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            return gson.fromJson(jsonString,object : TypeToken<ArrayList<City>>(){}.type)
        }catch (e:Exception){
            e.printStackTrace()
        }
       return ArrayList()
    }

    override suspend fun getCityWeatherDetail(cityId: Int): ExamplePojo {
       return weatherApi.searchById(cityId)
    }


    override suspend fun getTemrature(): String {
        return weatherApi.search("London").main?.temp.toString()
    }


}




