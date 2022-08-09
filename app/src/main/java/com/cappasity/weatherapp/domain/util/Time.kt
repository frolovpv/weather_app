package com.cappasity.weatherapp.domain.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object Time {

   @SuppressLint("SimpleDateFormat")
   fun correctTimeFormat(time: Long): String{
       if (time == 0L) return "-"
       return SimpleDateFormat("dd.MM.yyy HH:mm").format(Date(time))
   }

    @SuppressLint("SimpleDateFormat")
   fun hourMinTimeFormat(time: Long?): String{
       if (time == null || time == 0L) return "-"
       return SimpleDateFormat("hh:mm a").format(Date(time))
   }
}