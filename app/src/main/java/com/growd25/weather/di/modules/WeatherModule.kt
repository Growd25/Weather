package com.growd25.weather.di.modules

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.growd25.weather.data.net.WeatherApi
import com.growd25.weather.repository.DefaultWeatherRepository
import com.growd25.weather.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class WeatherModule(private val context: Context) {

    @Provides
    fun provideWeatherRepository(): WeatherRepository {
        return DefaultWeatherRepository(
            provideRetrofitService(provideRetrofit()),
            context,
            provideGson()
        )
    }

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