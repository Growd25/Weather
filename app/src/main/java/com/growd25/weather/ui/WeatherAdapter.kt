package com.growd25.weather.ui

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.growd25.weather.utils.inflate
import com.growd25.weather.R
import com.growd25.weather.entities.City


class WeatherAdapter(private val listenerItem: (c: City) -> Unit) :
    ListAdapter<City, WeatherAdapter.WeatherViewHolder>(WeatherDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(parent.inflate(R.layout.item_city), listenerItem)
    }


    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class WeatherViewHolder(itemView: View, listener: (city: City) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private lateinit var city: City
        private val nameCity: TextView = itemView.findViewById(R.id.nameTextView)

        init {
            itemView.setOnClickListener { listener.invoke(city) }
        }

        fun bind(city: City) {
            this.city = city
            nameCity.text = city.name
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