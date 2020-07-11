package com.growd25.weather.ui.details

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.growd25.weather.R
import com.growd25.weather.entities.model.ExamplePojo
import com.growd25.weather.presentation.DetailViewModel
import com.growd25.weather.presentation.factory.DetailViewModelFactory
import com.growd25.weather.ui.list.WeatherFragment.Companion.CITY_KEY
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_city_detail.*
import javax.inject.Inject

class WeatherDetailsFragment : Fragment(R.layout.fragment_city_detail) {


    @Inject lateinit var detailViewModelFactory: DetailViewModelFactory
    private lateinit var viewModelDetailsFragment: DetailViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelDetailsFragment = ViewModelProvider(this, detailViewModelFactory).get(DetailViewModel::class.java)
        searchCity()
        viewModelDetailsFragment._city.observe(viewLifecycleOwner, Observer {
            setData(it)
        })
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun searchCity() {
        val cityId: Int? = arguments?.getInt(CITY_KEY)
        viewModelDetailsFragment.getCityById(requireNotNull(cityId))
    }

    private fun setData(examplePojo: ExamplePojo) {
        tempDetailTextView.text = examplePojo.main?.temp.toString()
        descriprionTextView.text = examplePojo.name
        val iconId = examplePojo.weather?.get(0)?.icon
        Glide.with(this).load(getUrlIcon(iconId)).into(cityIconImageView)
    }


    companion object {
        fun getUrlIcon(iconId: String?): String {
            return "https://openweathermap.org/img/wn/$iconId@2x.png"

        }

        fun newInstance(cityId: Int? = null): WeatherDetailsFragment {
            return WeatherDetailsFragment().apply {
                arguments = Bundle().apply { putInt(CITY_KEY, requireNotNull(cityId)) }
            }
        }
    }
}
