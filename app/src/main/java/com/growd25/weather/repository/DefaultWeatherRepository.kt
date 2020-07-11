package com.growd25.weather.repository

import com.growd25.weather.data.WeatherAssetManager
import com.growd25.weather.data.net.WeatherApi
import com.growd25.weather.entities.City
import com.growd25.weather.entities.CityResult
import com.growd25.weather.entities.model.CityWeather
import javax.inject.Inject


class DefaultWeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherAssetManager: WeatherAssetManager
) : WeatherRepository {


    override suspend fun getListCities():CityResult{
       return weatherAssetManager.getListCities()
    }

    override suspend fun getCityWeatherDetail(cityId: Int): CityWeather {
        return weatherApi.getCityWeatherDetail(cityId)
    }
}




