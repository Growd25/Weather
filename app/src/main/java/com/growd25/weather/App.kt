package com.growd25.weather

import android.app.Application
import com.growd25.weather.di.DaggerWeatherComponent
import com.growd25.weather.di.WeatherComponent
import com.growd25.weather.di.modules.WeatherModule

class App : Application() {
    companion object {
        lateinit var component: WeatherComponent
    }


    override fun onCreate() {
        super.onCreate()
        initInjection()
    }

    private fun initInjection() {
        component = DaggerWeatherComponent.builder()
            .weatherModule(WeatherModule(applicationContext))
            .build()
    }
}