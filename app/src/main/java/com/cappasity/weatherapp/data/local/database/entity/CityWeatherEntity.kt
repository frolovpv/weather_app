package com.cappasity.weatherapp.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityWeatherEntity(
    @PrimaryKey (autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = "updateat") val updateAt: Long? = 0L,
    @ColumnInfo(name = "name") val name: String? = "",
    @ColumnInfo(name = "longitude") val longitude: Double? = 0.0,
    @ColumnInfo(name = "latitude") val latitude: Double? = 0.0,
    @ColumnInfo(name = "temperature") val temperature: String? = "",//текущая температура
    @ColumnInfo(name = "weather") val weather: String? = "", //погода(description)
    @ColumnInfo(name = "feelslike") val feelsLike: String? = "", //ощущается как
    @ColumnInfo(name = "windspeed") val windSpeed: String? = "", //Скорость ветра
    @ColumnInfo(name = "sunrise") val sunrise: String? = "", //Восход
    @ColumnInfo(name = "sunset") val sunset: String? = "", //Закат
    @ColumnInfo(name = "tempmin") val tempMin: String? = "", //мин
    @ColumnInfo(name = "tempmax") val tempMax: String? = "", //макс

)