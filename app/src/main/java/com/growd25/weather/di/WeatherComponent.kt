package com.growd25.weather.di

import com.growd25.weather.di.modules.WeatherModule
import com.growd25.weather.presentation.WeatherViewModel
import com.growd25.weather.repository.DefaultWeatherRepository
import com.growd25.weather.ui.weather.WeatherFragment
import dagger.Component


@Component(modules = [WeatherModule::class])
interface WeatherComponent {

    fun inject(weatherFragment: WeatherFragment)
    fun inject(weatherViewModel: WeatherViewModel)

}



