package com.cappasity.weatherapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cappasity.weatherapp.data.local.database.dao.CityWeatherEntityDao
import com.cappasity.weatherapp.data.local.database.entity.CityWeatherEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [
        CityWeatherEntity::class
    ]
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun cityWeatherEntityDao(): CityWeatherEntityDao
}