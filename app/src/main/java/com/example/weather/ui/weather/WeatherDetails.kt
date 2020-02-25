package com.example.weather.ui.weather

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.weather.R
import com.example.weather.data.model.ExamplePojo
import com.example.weather.ui.weather.WeatherFragment.Companion.CITY_KEY
import kotlinx.android.synthetic.main.fragment_city_detail.*

class WeatherDetails : Fragment(R.layout.fragment_city_detail) {
    private var examplePojo: ExamplePojo? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWeather()
    }


    private fun setWeather() {
        examplePojo = requireArguments().getParcelable(CITY_KEY)
        temperatureDetail.text = examplePojo?.main?.temp.toString()
        descriprion.text = examplePojo?.name
        val iconId = examplePojo?.weather?.get(0)?.icon
        Glide.with(this).load("https://openweathermap.org/img/wn/$iconId@2x.png").into(image_view)
    }


    companion object {
        fun newInstance(city: ExamplePojo? = null) = WeatherDetails().apply {
            arguments = Bundle().apply { putParcelable(CITY_KEY, city) }
        }
    }
}