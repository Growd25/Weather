package com.growd25.weather.repository

import com.growd25.weather.entities.City
import com.growd25.weather.entities.model.ExamplePojo

interface WeatherRepository {



    suspend fun getListCities(): List<City>
    suspend fun getCityWeatherDetail(cityId: Int): ExamplePojo


}