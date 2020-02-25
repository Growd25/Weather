package com.example.weather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.App
import com.example.weather.data.City
import com.example.weather.data.model.ExamplePojo
import com.example.weather.repository.WeatherRepository
import kotlinx.coroutines.*
import javax.inject.Inject


class WeatherViewModel @Inject constructor () : ViewModel() {
    @Inject lateinit var weatherRepository: WeatherRepository
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val _cities = MutableLiveData<ArrayList<City>>()
    var cities: MutableLiveData<ArrayList<City>>

    init {
        App.component.inject(this)
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
        val a = coroutineScope.async {
            weatherRepository.getCityWeatherDetail(cityId)
        }
       return a.await()
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }

}











