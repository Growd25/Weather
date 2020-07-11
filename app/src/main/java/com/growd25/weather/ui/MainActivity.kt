package com.growd25.weather.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.growd25.weather.R
import com.growd25.weather.entities.model.ExamplePojo
import com.growd25.weather.ui.details.WeatherDetailsFragment
import com.growd25.weather.ui.list.WeatherFragment
import dagger.android.AndroidInjection
import dagger.android.HasAndroidInjector

class MainActivity : AppCompatActivity(), WeatherFragment.CityItemListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        attachFragment()
    }

    private fun attachFragment() {
        supportFragmentManager.run {
            if (findFragmentById(R.id.container) == null) {
                beginTransaction()
                    .add(R.id.container, WeatherFragment.newInstance())
                    .commit()
            }
        }
    }

    override fun clickedOnCity(cityId: Int) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, WeatherDetailsFragment.newInstance(cityId))
            .commit()
    }
}
