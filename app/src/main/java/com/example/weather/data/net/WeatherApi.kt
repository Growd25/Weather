package com.example.weather.data.net

import com.example.weather.data.model.ExamplePojo
import retrofit2.http.GET
import retrofit2.http.Query




interface WeatherApi {
    companion object{
        const val APP_ID = "b6907d289e10d714a6e88b30761fae22"
        const val WEATHER_URL = "https://openweathermap.org"
    }

    @GET("/data/2.5/weather?&appid=$APP_ID")
   suspend fun search(@Query("q")name:String): ExamplePojo

    @GET("/data/2.5/weather?&appid=$APP_ID")
    suspend fun searchById(@Query("id")idCity:Int): ExamplePojo



}