package com.cappasity.weatherapp.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseFragment(@LayoutRes resId: Int): Fragment(resId) {

    open val binding: ViewBinding by viewBinding()

    open fun initView(): ViewBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }


    fun navigateTo(directions: NavDirections){
        lifecycleScope.launch(Dispatchers.Main) {
            try {
                findNavController().navigate(directions)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
