package com.growd25.weather.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.growd25.weather.App
import com.growd25.weather.data.WeatherAssetManager
import com.growd25.weather.data.net.WeatherApi
import com.growd25.weather.repository.DefaultWeatherRepository
import com.growd25.weather.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetModule {

    @Singleton
    @Provides
    fun provideWeatherAssetManager(app:App, gson: Gson) = WeatherAssetManager(app,gson)

    @Provides
    fun provideRetrofitService(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(WeatherApi.WEATHER_URL)
            .build()
    }

}