package com.cappasity.weatherapp.data.repository

import android.location.Address

interface GeoRepository {

    fun findCity(query: String): List<Address>

}