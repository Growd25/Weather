package com.growd25.weather.ui.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.growd25.weather.R
import com.growd25.weather.presentation.MainViewModel
import com.growd25.weather.presentation.factory.WeatherViewModelFactory
import com.growd25.weather.ui.MainActivity
import com.growd25.weather.ui.WeatherAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_weather.*
import javax.inject.Inject

class WeatherFragment : Fragment(R.layout.fragment_weather) {


    @Inject lateinit var weatherFactory: WeatherViewModelFactory
    private lateinit var viewModel: MainViewModel
    private var listener: CityItemListener? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = WeatherAdapter { listener?.clickedOnCity(it.id) }
        viewModel = ViewModelProvider(this, weatherFactory).get(MainViewModel::class.java)
        setDataAdapter(adapter)
        weatherRecyclerView.adapter = adapter
    }


    private fun setDataAdapter(adapter: WeatherAdapter) {
        viewModel.cities.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }



    interface CityItemListener {
        fun clickedOnCity(cityId: Int)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        listener = (context as MainActivity)
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        const val CITY_KEY = "CITY_KEY"
        fun newInstance() = WeatherFragment()
    }




}


