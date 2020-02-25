package com.example.weather.ui.weather

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.weather.App
import com.example.weather.R
import com.example.weather.data.model.ExamplePojo
import com.example.weather.ui.MainActivity
import com.example.weather.ui.WeatherAdapter
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
        initViewModel()
        setRecyclerAndObserve()
    }

    private fun setRecyclerAndObserve() {
        viewModel.cities.observe(viewLifecycleOwner, Observer {
            val adapter = WeatherAdapter {
                coroutineScope.launch {
                    val result = viewModel.search(it.id)
                    listener.clickedOnCity(result)
                }
            }
            adapter.submitList(it)
            val a = GridLayoutManager(context, 2)
            weatherRecycler.layoutManager = a
            weatherRecycler.adapter = adapter
        })
    }

    private fun initViewModel() {
        App.component.inject(this)
        viewModel = ViewModelProvider(this, weatherFactory).get(WeatherViewModel::class.java)
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


