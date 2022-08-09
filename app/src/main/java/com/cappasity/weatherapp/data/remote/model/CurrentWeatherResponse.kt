package com.cappasity.weatherapp.data.remote.model
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class CurrentWeatherResponse(
    @Json(name = "base")
    val base: String? = null,
    @Json(name = "clouds")
    val clouds: Clouds? = null,
    @Json(name = "cod")
    val cod: Int? = null,
    @Json(name = "coord")
    val coord: Coord? = null,
    @Json(name = "dt")
    val dt: Int? = null,
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "main")
    val main: Main? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "sys")
    val sys: Sys? = null,
    @Json(name = "timezone")
    val timezone: Int? = null,
    @Json(name = "visibility")
    val visibility: Int? = null,
    @Json(name = "weather")
    val weather: List<Weather>? = null,
    @Json(name = "wind")
    val wind: Wind? = null
)

@JsonClass(generateAdapter = true)
data class Clouds(
    @Json(name = "all")
    val all: Int? = null
)

@JsonClass(generateAdapter = true)
data class Coord(
    @Json(name = "lat")
    val lat: Double? = null,
    @Json(name = "lon")
    val lon: Double? = null
)

@JsonClass(generateAdapter = true)
data class Main(
    @Json(name = "feels_like")
    val feelsLike: Double? = null,
    @Json(name = "humidity")
    val humidity: Int? = null,
    @Json(name = "pressure")
    val pressure: Int? = null,
    @Json(name = "temp")
    val temp: Double? = null,
    @Json(name = "temp_max")
    val tempMax: Double? = null,
    @Json(name = "temp_min")
    val tempMin: Double? = null
)

@JsonClass(generateAdapter = true)
data class Sys(
    @Json(name = "country")
    val country: String? = null,
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "sunrise")
    val sunrise: Int? = null,
    @Json(name = "sunset")
    val sunset: Int? = null,
    @Json(name = "type")
    val type: Int? = null
)

@JsonClass(generateAdapter = true)
data class Weather(
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "icon")
    val icon: String? = null,
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "main")
    val main: String? = null
)

@JsonClass(generateAdapter = true)
data class Wind(
    @Json(name = "deg")
    val deg: Int? = null,
    @Json(name = "gust")
    val gust: Double? = null,
    @Json(name = "speed")
    val speed: Double? = null
)