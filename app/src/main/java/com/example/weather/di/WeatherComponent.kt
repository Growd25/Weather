package com.example.weather.di

import com.example.weather.di.modules.ApplicationModule
import com.example.weather.di.modules.NetWorkModule
import com.example.weather.repository.DefaultWeatherRepository
import com.example.weather.ui.weather.WeatherFragment
import com.example.weather.ui.weather.WeatherViewModel
import dagger.Component
import javax.inject.Singleton


@Component(modules = [ApplicationModule::class,NetWorkModule::class])
interface WeatherComponent{

    fun inject(defaultWeatherRepository: DefaultWeatherRepository)
    fun inject(weatherFragment: WeatherFragment)
    fun inject(weatherViewModel: WeatherViewModel)

}



