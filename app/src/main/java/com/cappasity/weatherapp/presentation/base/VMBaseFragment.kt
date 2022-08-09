package com.cappasity.weatherapp.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleOwner

/**
 * фрагмент с использованием viewmodel
 */
abstract class VMBaseFragment<T : BaseViewModel>(@LayoutRes resId: Int) : BaseFragment(resId) {

    lateinit var viewModel: T
    abstract fun provideViewModel(): T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = provideViewModel()
        setObservers(viewLifecycleOwner)
    }

    open fun setObservers(viewOwner: LifecycleOwner) {}
}