package com.cappasity.weatherapp.presentation.fragment

import android.annotation.SuppressLint
import androidx.lifecycle.LifecycleOwner
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cappasity.weatherapp.R
import com.cappasity.weatherapp.databinding.FragmentCityWeatherBinding
import com.cappasity.weatherapp.di.main.MainInjector
import com.cappasity.weatherapp.domain.extension.createViewModel
import com.cappasity.weatherapp.domain.util.Time.correctTimeFormat
import com.cappasity.weatherapp.domain.util.Time.hourMinTimeFormat
import com.cappasity.weatherapp.presentation.base.BaseFragment
import com.cappasity.weatherapp.presentation.base.VMBaseFragment
import com.cappasity.weatherapp.presentation.model.PWeatherForecastModel
import com.cappasity.weatherapp.presentation.model.PWeatherModel
import com.cappasity.weatherapp.presentation.viewmodel.MainViewModel

class CityWeatherFragment: VMBaseFragment<MainViewModel>(R.layout.fragment_city_weather) {

    override val binding: FragmentCityWeatherBinding by viewBinding(FragmentCityWeatherBinding::bind)

    override fun provideViewModel(): MainViewModel = createViewModel {
        MainInjector.mainComponent.mainViewModel
    }

    @SuppressLint("SetTextI18n")
    override fun setObservers(viewOwner: LifecycleOwner) {
        super.setObservers(viewOwner)
        arguments?.takeIf { it.containsKey("position") }?.apply {
            viewModel.citiesState.observe(viewOwner){
                it[getInt("position")].let{ item->
                    binding.apply {
                        cityName.text =  item.name
                        cityCurrentWeatherTemp.text = "${item.temperature}°C"
                        cityCurrentWeather.text = "${item.weather}"
                        updatedAt.text = "Информация актуальна на: ${correctTimeFormat(item.updateAt ?: 0L)}"
                        feelsLike.text = "Ощущается как ${item.feelsLike}°C"
                        windSpeed.text = "Скорость ветра ${item.windSpeed} м/с"
                        sunrise.text = hourMinTimeFormat(item.sunrise?.toLong())
                        sunset.text = hourMinTimeFormat(item.sunset?.toLong())
                        minTemp.text = "Мин. температура ${item.tempMin}°C"
                        maxTemp.text = "Макс. температура ${item.tempMax}°C"
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        arguments?.takeIf { it.containsKey("position") }?.apply {
            viewModel.loadWeatherDataForCity(getInt("position"))
        }
    }

}