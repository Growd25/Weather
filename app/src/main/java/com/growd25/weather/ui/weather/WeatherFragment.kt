package com.growd25.weather.ui.weather

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.growd25.weather.App
import com.growd25.weather.R
import com.growd25.weather.entities.City
import com.growd25.weather.entities.model.ExamplePojo
import com.growd25.weather.presentation.WeatherViewModel
import com.growd25.weather.presentation.WeatherViewModelFactory
import com.growd25.weather.ui.MainActivity
import com.growd25.weather.ui.WeatherAdapter
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherFragment : Fragment(R.layout.fragment_weather) {
    @Inject lateinit var weatherFactory: WeatherViewModelFactory
    private lateinit var viewModel: WeatherViewModel
    private lateinit var listener: CityItemListener
    private val coroutineScope = CoroutineScope(Dispatchers.Main)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = WeatherAdapter { searchCity(it)}
        App.component.inject(this)
        viewModel = ViewModelProvider(this, weatherFactory).get(WeatherViewModel::class.java)
        setDataAdapter(adapter)
    }

    private fun searchCity(city: City){
        coroutineScope.launch {
            val result = viewModel.search(city.id)
            listener.clickedOnCity(result)
        }
    }
    private fun setDataAdapter(adapter:WeatherAdapter){
        viewModel.cities.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    companion object {
        const val CITY_KEY = "CITY_KEY"
        fun newInstance() = WeatherFragment()
    }

    interface CityItemListener {
        fun clickedOnCity(examplePojo: ExamplePojo)
    }

    override fun onDetach() {
        super.onDetach()
        coroutineScope.cancel()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = (context as MainActivity)
    }
}


