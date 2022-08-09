package com.cappasity.weatherapp.domain.util

object Temperature {

    fun toNormal(value: Double): String{
       return ((value - 32) / 1.8).toString()
    }

}