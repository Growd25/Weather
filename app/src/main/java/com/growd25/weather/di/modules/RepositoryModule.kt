package com.growd25.weather.di.modules

import com.growd25.weather.repository.DefaultWeatherRepository
import com.growd25.weather.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun provideRepository(repositoryImpl:DefaultWeatherRepository):WeatherRepository

}