package com.growd25.weather.ui.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.growd25.weather.R
import com.growd25.weather.entities.CityResult
import com.growd25.weather.presentation.MainViewModel
import com.growd25.weather.presentation.factory.WeatherViewModelFactory
import com.growd25.weather.ui.MainActivity
import com.growd25.weather.ui.WeatherAdapter
import com.growd25.weather.utils.toast
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_weather.*
import javax.inject.Inject

class WeatherFragment : Fragment(R.layout.fragment_weather) {

    @Inject
    lateinit var weatherFactory: WeatherViewModelFactory
    private var listener: CityItemListener? = null
    private lateinit var adapter: WeatherAdapter
    private lateinit var  viewModel: MainViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        listener = (context as MainActivity)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         adapter = WeatherAdapter { listener?.clickedOnCity(it.id) }
       viewModel = ViewModelProvider(this, weatherFactory).get(MainViewModel::class.java)
        viewModel.cities.observe(viewLifecycleOwner, Observer(this::acceptData))
        weatherRecyclerView.adapter = adapter
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun acceptData(cityResult: CityResult) {
        if (cityResult.error!=null){
            toast(R.string.load_error)
            viewModel.onErrorShow()
        }
        adapter.submitList(cityResult.cities)
    }


    interface CityItemListener {
        fun clickedOnCity(cityId: Int)
    }

    companion object {
        const val CITY_KEY = "CITY_KEY"
        fun newInstance() = WeatherFragment()
    }


}


