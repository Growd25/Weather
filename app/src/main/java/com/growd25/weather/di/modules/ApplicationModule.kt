package com.growd25.weather.di.modules

import com.growd25.weather.ui.details.WeatherDetailsFragment
import com.growd25.weather.ui.list.WeatherFragment
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector


@Module(includes = [AndroidInjectionModule::class])
abstract class ApplicationModule {

    @ContributesAndroidInjector
    abstract fun contributesWeatherFragment(): WeatherFragment

    @ContributesAndroidInjector
    abstract fun contributesWeatherDetailsFragment(): WeatherDetailsFragment

}