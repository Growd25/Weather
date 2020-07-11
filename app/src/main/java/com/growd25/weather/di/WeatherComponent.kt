package com.growd25.weather.di

import com.growd25.weather.App
import com.growd25.weather.di.modules.ApplicationModule
import com.growd25.weather.di.modules.NetModule
import com.growd25.weather.di.modules.RepositoryModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetModule::class, RepositoryModule::class])
interface WeatherComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<App>

}



