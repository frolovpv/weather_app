package com.cappasity.weatherapp.data.repository

import com.cappasity.weatherapp.data.local.database.AppDatabase
import com.cappasity.weatherapp.data.local.database.dao.CityWeatherEntityDao
import com.cappasity.weatherapp.data.local.database.entity.CityWeatherEntity
import com.cappasity.weatherapp.data.remote.RetrofitService
import retrofit2.HttpException
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CityWeatherRepositoryDataSource @Inject constructor(
    private val database: AppDatabase,
    private val retrofitService: RetrofitService
):CityWeatherRepository {

    override fun refreshCitiesList(): List<CityWeatherEntity> {
        checkStartInfo()
        return database.cityWeatherEntityDao().getAll()
    }

    override fun deleteCity(cityEntityId: Long) {
        database.cityWeatherEntityDao().delete(cityEntityId)
    }

    override fun getCities(): List<CityWeatherEntity> {
        return database.cityWeatherEntityDao().getAll()
    }

    override fun addCity(entity: CityWeatherEntity) {
        database.cityWeatherEntityDao().insert(entity)
    }


    override suspend fun loadWeatherData(loadingEnd: () -> Unit) {
        database.cityWeatherEntityDao().apply {
            getAll().forEach { city->
                loadCityInfo(city,this)
            }
            loadingEnd()
        }
    }

    override suspend fun loadWeatherDataForCity(position: Int) {
        database.cityWeatherEntityDao().apply {
            getAll()[position].let { city ->
                loadCityInfo(city,this)
            }
        }
    }

    private suspend fun loadCityInfo(city: CityWeatherEntity, dao: CityWeatherEntityDao){
        if (Calendar.getInstance().timeInMillis > ((city.updateAt ?: 0L) + TimeUnit.MINUTES.toMillis(20))){
            try {
                retrofitService.weatherApi().currentWeather((city.latitude ?: 0.0).toString(),(city.longitude ?: 0.0).toString())
            } catch (e: HttpException) {
                null
            } catch (e: IOException) {
                null
            }?.let { response  ->
                dao.updateItem(
                    city.id ?: 0L,
                    Calendar.getInstance().timeInMillis,
                    response.main?.temp?.toInt().toString(),
                        response.weather?.get(0)?.description ?: "",
                        response.main?.feelsLike?.toInt().toString(),
                        response.wind?.speed.toString(),
                        response.sys?.sunrise.toString(),
                        response.sys?.sunset.toString(),
                        response.main?.tempMin.toString(),
                        response.main?.tempMax.toString()
                )
            }
        }
    }


    /**
     * проверяем начальные данные(Москва и Санкт-Петербург)
     */
    private fun checkStartInfo() {
        database.cityWeatherEntityDao().apply {
            if (getAll().isEmpty()) {
                insert(CityWeatherEntity(null, 0L,"Москва",37.618423,55.751244))
                insert(CityWeatherEntity(null, 0L,"Санкт-Петербург",30.3350986,59.9342802))
            }
        }
    }

}