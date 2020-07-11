package com.growd25.weather.repository

import com.growd25.weather.entities.City
import com.growd25.weather.entities.CityResult
import com.growd25.weather.entities.model.CityWeather

interface WeatherRepository {



    suspend fun getListCities(): CityResult
    suspend fun getCityWeatherDetail(cityId: Int): CityWeather


}