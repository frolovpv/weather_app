package com.cappasity.weatherapp.di.network


import com.cappasity.weatherapp.data.remote.RetrofitService
import dagger.Component

@Component(
    modules = [NetworkModule::class]
)
interface NetworkComponent {
    val retrofitService: RetrofitService
}