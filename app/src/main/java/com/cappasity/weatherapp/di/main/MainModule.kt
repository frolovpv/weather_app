package com.cappasity.weatherapp.di.main

import android.content.Context
import android.location.Geocoder
import com.cappasity.weatherapp.data.repository.CityWeatherRepository
import com.cappasity.weatherapp.data.repository.CityWeatherRepositoryDataSource
import com.cappasity.weatherapp.data.repository.GeoRepository
import com.cappasity.weatherapp.data.repository.GeoRepositoryDataSource
import com.cappasity.weatherapp.di.database.DatabaseComponent
import com.cappasity.weatherapp.di.network.NetworkComponent
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
class MainModule(
    private val databaseComponent: DatabaseComponent,
    private val networkComponent: NetworkComponent,
    private val context: Context
) {

    @Singleton
    @Provides
    fun cityWeatherRepository(): CityWeatherRepository =
        CityWeatherRepositoryDataSource(databaseComponent.database,networkComponent.retrofitService)


    @Singleton
    @Provides
    fun geoRepository(): GeoRepository =
        GeoRepositoryDataSource(Geocoder(context, Locale.Builder().setLanguage("RU").setScript("Latn").setRegion("RS").build()))


}
