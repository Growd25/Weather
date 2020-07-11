package com.growd25.weather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.growd25.weather.entities.model.CityWeather
import com.growd25.weather.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: WeatherRepository) :
    ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private   val _city = MutableLiveData<CityWeather>()
     var city: LiveData<CityWeather> = _city


    fun getCityById(cityId: Int){
        coroutineScope.launch {
          val result = repository.getCityWeatherDetail(cityId)
            _city.value = result

        }
    }


    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}