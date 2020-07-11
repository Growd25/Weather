package com.growd25.weather.repository

import android.util.Log
import com.growd25.weather.data.WeatherAssetManager
import com.growd25.weather.data.net.WeatherApi
import com.growd25.weather.entities.City
import com.growd25.weather.entities.model.ExamplePojo
import javax.inject.Inject


class DefaultWeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi, private val weatherAssetManager: WeatherAssetManager
) : WeatherRepository {


    override suspend fun getListCities(): List<City> {
       return weatherAssetManager.getListCities()
    }

    override suspend fun getCityWeatherDetail(cityId: Int): ExamplePojo {
        return weatherApi.searchById(cityId)
    }
}




