package com.cappasity.weatherapp.data.repository

import com.cappasity.weatherapp.data.local.database.entity.CityWeatherEntity
import com.cappasity.weatherapp.data.remote.model.CurrentWeatherResponse
import java.text.FieldPosition

interface CityWeatherRepository {

    fun refreshCitiesList(): List<CityWeatherEntity>

    fun deleteCity(cityEntityId: Long)

    fun getCities(): List<CityWeatherEntity>

    fun addCity(entity: CityWeatherEntity)

    suspend fun loadWeatherData(loadingEnd: () -> Unit)

    suspend fun loadWeatherDataForCity(position: Int)

}