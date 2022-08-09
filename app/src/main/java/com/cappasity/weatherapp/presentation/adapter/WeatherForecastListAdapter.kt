package com.cappasity.weatherapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cappasity.weatherapp.R
import com.cappasity.weatherapp.presentation.model.PWeatherForecastModel

class WeatherForecastListAdapter: RecyclerView.Adapter<WeatherForecastListAdapter.WeatherForecastListViewHolder>() {

    private var forecastsList: MutableList<PWeatherForecastModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.vh_weather_forecast_list_item, parent, false)
        return WeatherForecastListViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherForecastListViewHolder, position: Int) {
        holder.onBind(forecastsList[position], position)
    }

    override fun getItemCount(): Int {
        return forecastsList.size
    }

    fun refreshList(list: MutableList<PWeatherForecastModel>) {
        forecastsList.clear()
        forecastsList.addAll(list)
        notifyDataSetChanged()
    }

    inner class WeatherForecastListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val date: TextView = itemView.findViewById(R.id.date)

        fun onBind(model: PWeatherForecastModel, position: Int) {
            date.text = model.cityName
        }

    }
}

