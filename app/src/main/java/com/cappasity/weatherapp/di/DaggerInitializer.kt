package com.cappasity.weatherapp.di

import android.app.Application
import com.cappasity.weatherapp.di.database.DaggerDatabaseComponent
import com.cappasity.weatherapp.di.database.DatabaseInjector
import com.cappasity.weatherapp.di.database.DatabaseModule
import com.cappasity.weatherapp.di.main.DaggerMainComponent
import com.cappasity.weatherapp.di.main.MainInjector
import com.cappasity.weatherapp.di.main.MainModule
import com.cappasity.weatherapp.di.network.DaggerNetworkComponent
import com.cappasity.weatherapp.di.network.NetworkInjector
import com.cappasity.weatherapp.di.network.NetworkModule

object DaggerInitializer {

    fun init(application: Application){

        val databaseComponent = DaggerDatabaseComponent.builder().databaseModule(DatabaseModule(application)).build()
        DatabaseInjector.databaseComponent = databaseComponent

        val networkComponent = DaggerNetworkComponent.builder().networkModule(NetworkModule()).build()
        NetworkInjector.networkComponent = networkComponent

        DaggerMainComponent.builder().mainModule(MainModule(databaseComponent,networkComponent,application)).build().apply {
            MainInjector.mainComponent = this
        }

    }

}