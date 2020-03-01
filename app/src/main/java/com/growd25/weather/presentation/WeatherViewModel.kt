package com.growd25.weather.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.growd25.weather.App
import com.growd25.weather.entities.City
import com.growd25.weather.entities.model.ExamplePojo
import com.growd25.weather.repository.WeatherRepository
import kotlinx.coroutines.*
import javax.inject.Inject


class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val _cities = MutableLiveData<ArrayList<City>>()
    var cities: MutableLiveData<ArrayList<City>>

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


    suspend fun search(cityId: Int): ExamplePojo {
        val result = coroutineScope.async {
            weatherRepository.getCityWeatherDetail(cityId)
        }
        return result.await()
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}











