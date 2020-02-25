package com.example.weather.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.R
import com.example.weather.data.City
import com.example.weather.data.model.ExamplePojo
import com.example.weather.ui.weather.WeatherDetails
import com.example.weather.ui.weather.WeatherFragment

class MainActivity : AppCompatActivity(),WeatherFragment.CityItemListener {


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

    override fun clickedOnCity(examplePojo: ExamplePojo) {
      supportFragmentManager.beginTransaction()
          .addToBackStack(null)
          .replace(R.id.container,WeatherDetails.newInstance(examplePojo))
          .commit()
    }
}









