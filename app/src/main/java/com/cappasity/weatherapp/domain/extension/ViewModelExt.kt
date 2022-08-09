package com.cappasity.weatherapp.domain.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

lateinit var owner: ViewModelStoreOwner


inline fun <reified T : ViewModel> Fragment.createViewModel(crossinline factory: () -> T): T =
    T::class.java.let { classT ->
        val vm = ViewModelProvider(
            owner,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass == classT) {
                        @Suppress("UNCHECKED_CAST")
                        return factory() as T
                    }
                    throw IllegalArgumentException("Unexpected argument: $modelClass")

                }
            })[classT]
        vm
    }

inline fun <reified T : ViewModel> ViewModelStoreOwner.createActivityViewModel(crossinline factory: () -> T): T =
    T::class.java.let { classT ->
        val vm = ViewModelProvider(
            if (!::owner.isInitialized) this else owner,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass == classT) {
                        @Suppress("UNCHECKED_CAST")
                        return factory() as T
                    }
                    throw IllegalArgumentException("Unexpected argument: $modelClass")
                }
            })[classT]
        vm
    }
