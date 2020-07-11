package com.growd25.weather.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.growd25.weather.App
import com.growd25.weather.entities.City
import com.growd25.weather.entities.model.ExamplePojo
import com.growd25.weather.repository.WeatherRepository
import kotlinx.coroutines.*
import javax.inject.Inject


class MainViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val _cities = MutableLiveData<List<City>>()
    var cities: LiveData<List<City>>

    init {
        coroutineScope.launch { setLiveData() }
        cities = _cities
    }

    private suspend fun setLiveData() {
        val cities = withContext(Dispatchers.IO) {
            weatherRepository.getListCities()
        }
        _cities.value = cities
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}











