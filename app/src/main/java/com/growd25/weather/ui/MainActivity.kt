package com.growd25.weather.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.growd25.weather.R
import com.growd25.weather.ui.details.WeatherDetailsFragment
import com.growd25.weather.ui.list.WeatherFragment
import com.growd25.weather.utils.addFragment
import com.growd25.weather.utils.replaceFragment

class MainActivity : AppCompatActivity(), WeatherFragment.CityItemListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportFragmentManager.findFragmentById(R.id.container) == null) {
            addFragment(R.id.container, WeatherFragment.newInstance())
        }
    }

    override fun clickedOnCity(cityId: Int) {
        replaceFragment(R.id.container, WeatherDetailsFragment.newInstance(cityId))
    }


}



