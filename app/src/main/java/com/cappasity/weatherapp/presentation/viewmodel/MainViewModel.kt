package com.cappasity.weatherapp.presentation.viewmodel

import android.location.Address
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cappasity.weatherapp.data.local.database.entity.CityWeatherEntity
import com.cappasity.weatherapp.data.repository.CityWeatherRepository
import com.cappasity.weatherapp.data.repository.GeoRepository
import com.cappasity.weatherapp.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val cityWeatherRepository: CityWeatherRepository,
    private val geoRepository: GeoRepository
): BaseViewModel() {

    val citiesState = MutableLiveData<List<CityWeatherEntity>>().apply {
        postValue(listOf())
    }

    val querySuggestions = MutableLiveData<List<Address>>().apply {
        postValue(listOf())
    }

    fun hasNotCities(): Boolean {
       return cityWeatherRepository.getCities().isEmpty()
    }

   fun refreshCitiesList() {
        citiesState.postValue(cityWeatherRepository.refreshCitiesList())
    }

    fun deleteCity(cityEntityId: Long) {
        cityWeatherRepository.deleteCity(cityEntityId)
    }

    fun addCity(entity: CityWeatherEntity) {
        cityWeatherRepository.addCity(entity)
    }

    fun findCity(query: String) {
        try {
            querySuggestions.postValue(geoRepository.findCity(query))
        } catch (e: Exception) {
            querySuggestions.postValue(listOf())
        }
    }

    /**
     * обноовление информамции по всем городам
     */
    fun loadWeatherData(loadingEnd: () -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            cityWeatherRepository.loadWeatherData(loadingEnd)
            refreshCitiesList()
        }
    }

    /**
     * обноовление информамции для выбранного города
     */
    fun loadWeatherDataForCity(position: Int){
        viewModelScope.launch(Dispatchers.IO) {
            cityWeatherRepository.loadWeatherDataForCity(position)
            refreshCitiesList()
        }
    }
}