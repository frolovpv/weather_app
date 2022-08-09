package com.cappasity.weatherapp.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cappasity.weatherapp.domain.extension.owner

abstract class BaseActivity<T : BaseViewModel>: AppCompatActivity() {

    lateinit var viewModel: T
    abstract fun provideViewModel(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = provideViewModel()
        owner = this

    }
}