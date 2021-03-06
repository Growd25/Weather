package com.growd25.weather.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.growd25.weather.presentation.DetailViewModel
import com.growd25.weather.presentation.MainViewModel
import com.growd25.weather.repository.WeatherRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailViewModelFactory @Inject constructor(private val weatherRepository: WeatherRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(
                weatherRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}