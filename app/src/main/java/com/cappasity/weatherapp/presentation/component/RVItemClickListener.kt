package com.cappasity.weatherapp.presentation.component

import android.view.View
import com.cappasity.weatherapp.presentation.model.PWeatherModel

interface RVItemClickListener {
    fun onItemClick(view: View, position: Int, model: PWeatherModel)
    fun onDeleteClick(view: View, position: Int,model: PWeatherModel)
}
