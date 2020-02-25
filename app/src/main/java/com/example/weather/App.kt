package com.example.weather

import android.app.Application
import com.example.weather.di.DaggerWeatherComponent
import com.example.weather.di.WeatherComponent
import com.example.weather.di.modules.ApplicationModule
import com.example.weather.di.modules.NetWorkModule

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
            .netWorkModule(NetWorkModule())
            .applicationModule(ApplicationModule(this))
            .build()
    }
}