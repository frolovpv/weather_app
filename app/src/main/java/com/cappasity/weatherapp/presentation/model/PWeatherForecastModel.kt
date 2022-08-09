package com.cappasity.weatherapp.presentation.model

data class PWeatherForecastModel(
    val id: Long,
    val cityName: String,
    val maxTemp: String,
    val minTemp: String,
    val date: String,
    val weather: String
)