package com.cappasity.weatherapp.di.database

import com.cappasity.weatherapp.data.local.database.AppDatabase
import dagger.Component

@Component(
    modules = [DatabaseModule::class]
)
interface DatabaseComponent {
    val database: AppDatabase
}