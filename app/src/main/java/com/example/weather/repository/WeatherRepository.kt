package com.example.weather.repository

import com.example.weather.data.City
import com.example.weather.data.model.ExamplePojo

interface WeatherRepository {


   suspend fun getTemrature():String
   suspend fun getListCities():ArrayList<City>
   suspend fun getCityWeatherDetail(cityId:Int):ExamplePojo



}