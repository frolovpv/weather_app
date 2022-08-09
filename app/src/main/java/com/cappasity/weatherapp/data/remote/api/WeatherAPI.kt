package com.cappasity.weatherapp.data.remote.api

import com.cappasity.weatherapp.data.remote.model.CurrentWeatherResponse
import com.cappasity.weatherapp.domain.Constants.API_KEY
import com.cappasity.weatherapp.domain.Constants.API_KEY_FW
import com.cappasity.weatherapp.domain.Constants.API_LANG
import com.cappasity.weatherapp.domain.Constants.API_URL
import com.cappasity.weatherapp.domain.Constants.FORECAST_CNT
import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {


    /**
     * Получение информации о погоде.
     */
    @GET(API_URL + "weather?&appid=$API_KEY&lang=$API_LANG&units=metric")
    suspend fun currentWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): CurrentWeatherResponse?

    /**
     * Прогноз погоды на период FORECAST_CNT дней.
     */
    @GET(API_URL + "forecast/daily?&appid=$API_KEY_FW&cnt=$FORECAST_CNT&lang=$API_LANG&units=metric")
    suspend fun forecastWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
    ): JSONObject?


}