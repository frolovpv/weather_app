package com.cappasity.weatherapp.di.network

import com.cappasity.weatherapp.data.remote.RetrofitService
import dagger.Module
import dagger.Provides

@Module
class NetworkModule() {

    @Provides
    fun retrofitService() = RetrofitService()

}