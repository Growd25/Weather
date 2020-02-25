package com.example.weather.di.modules

import com.example.weather.data.net.WeatherApi
import com.example.weather.repository.DefaultWeatherRepository
import com.example.weather.repository.WeatherRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetWorkModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideWeatherRepository():WeatherRepository {
        return   DefaultWeatherRepository()
    }

    @Provides
    fun provideRetrofitService(retrofit: Retrofit):WeatherApi{
        return retrofit.create(WeatherApi::class.java)
    }

    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
           .addConverterFactory(GsonConverterFactory.create())
           .baseUrl(WeatherApi.WEATHER_URL)
           .build()
    }

}