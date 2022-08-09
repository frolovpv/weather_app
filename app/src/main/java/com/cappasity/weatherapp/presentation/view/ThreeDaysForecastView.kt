package com.cappasity.weatherapp.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.cappasity.weatherapp.databinding.ViewThreeDaysForecastBinding
import com.cappasity.weatherapp.domain.Constants.FORECAST_CNT
import com.cappasity.weatherapp.presentation.adapter.WeatherForecastListAdapter
import com.cappasity.weatherapp.presentation.model.PWeatherForecastModel
import com.cappasity.weatherapp.presentation.model.PWeatherModel

class ThreeDaysForecastView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding = ViewThreeDaysForecastBinding.inflate(LayoutInflater.from(context), this)
    private val weatherForecastListAdapter = WeatherForecastListAdapter()

    init {
        binding.weatherForecastRV.adapter = weatherForecastListAdapter
        binding.title.text = "Прогноз погоды на $FORECAST_CNT дня"
    }

    fun refreshList(list: MutableList<PWeatherForecastModel>){
        weatherForecastListAdapter.refreshList(list)
    }


}