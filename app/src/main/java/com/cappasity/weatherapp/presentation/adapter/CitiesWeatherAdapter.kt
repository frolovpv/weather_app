package com.cappasity.weatherapp.presentation.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cappasity.weatherapp.presentation.fragment.CityWeatherFragment

class CitiesWeatherAdapter(fragment: Fragment, private val itemsCount: Int) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = itemsCount

    override fun createFragment(position: Int): Fragment {
        return CityWeatherFragment().apply {
            this.arguments =  Bundle().apply {
                putInt("position",position)
            }
        }
    }

}