package com.growd25.weather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.growd25.weather.entities.City
import com.growd25.weather.entities.CityResult
import com.growd25.weather.repository.WeatherRepository
import kotlinx.coroutines.*
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val _cities = MutableLiveData<CityResult>()
    val cities: LiveData<CityResult> = _cities

    init {
        coroutineScope.launch { loadCities() }
    }

    private suspend fun loadCities() {
        val cities = withContext(Dispatchers.IO) {
            weatherRepository.getListCities()
        }
        _cities.value = cities
    }

    fun onErrorShow(){
        _cities.value = _cities.value?.copy(error = null)
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}
