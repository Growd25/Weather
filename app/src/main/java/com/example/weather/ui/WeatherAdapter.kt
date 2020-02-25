package com.example.weather.ui

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.inflate
import com.example.weather.R
import com.example.weather.data.City

class WeatherAdapter(private val lambda: (c: City) -> Unit) :
    androidx.recyclerview.widget.ListAdapter<City,
            WeatherAdapter.WeatherViewHolder>(WeatherDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder =
         WeatherViewHolder(parent.inflate(R.layout.city_item), lambda)

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
       holder.bind(getItem(position))
    }

    class WeatherViewHolder(itemView: View, lambda: (city: City) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private lateinit var city: City
        private val cityId: TextView = itemView.findViewById(R.id.textView)
        private val nameCity: TextView = itemView.findViewById(R.id.textView2)
        private val country: TextView = itemView.findViewById(R.id.textView3)

        init {
            itemView.setOnClickListener { lambda.invoke(city) }
        }

        fun bind(city: City) {
            this.city = city
            cityId.text = city.id.toString()
            nameCity.text = city.name
            country.text = city.country
        }

    }

    class WeatherDiffCallBack : DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem == newItem
        }

    }
}