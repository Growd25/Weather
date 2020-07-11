package com.growd25.weather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.growd25.weather.entities.model.ExamplePojo
import com.growd25.weather.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: WeatherRepository) :
    ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val city = MutableLiveData<ExamplePojo>()
     var _city: LiveData<ExamplePojo>

    init {
        _city = city
    }

    fun getCityById(cityId: Int){
        coroutineScope.launch {
          val result = repository.getCityWeatherDetail(cityId)
            city.value = result
            _city = city
        }
    }


    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}