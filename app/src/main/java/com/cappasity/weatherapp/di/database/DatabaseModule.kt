package com.cappasity.weatherapp.di.database

import android.content.Context
import androidx.room.Room
import com.cappasity.weatherapp.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides


@Module
class DatabaseModule(private val context: Context) {

    @Provides
    fun database() = Room.databaseBuilder(context, AppDatabase::class.java, "cappasity_app_db")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

}