package com.cappasity.weatherapp.di.main

import com.cappasity.weatherapp.presentation.viewmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [MainModule::class]
)
interface MainComponent {
    val mainViewModel: MainViewModel
}