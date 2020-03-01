package com.growd25.weather.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(val id: Int, val name: String, val country: String) : Parcelable






