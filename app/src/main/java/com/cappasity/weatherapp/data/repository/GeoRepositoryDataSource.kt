package com.cappasity.weatherapp.data.repository

import android.location.Address
import android.location.Geocoder
import javax.inject.Inject

class GeoRepositoryDataSource @Inject constructor(
    private val geocoder: Geocoder
): GeoRepository {


    override fun findCity(query: String): List<Address> {
        return geocoder.getFromLocationName(query, 8) ?: emptyList()
    }


}