package com.cappasity.weatherapp.data.remote

import com.cappasity.weatherapp.data.remote.api.WeatherAPI
import com.cappasity.weatherapp.domain.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitService {

    private fun getOkHttpClient(): OkHttpClient {
        val okClient = OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return okClient.build()
    }

    private inline fun <reified T> buildRetrofit(baseUrl: String): T {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(JSONObjectAdapter)
            .build()
        return Retrofit.Builder().client(getOkHttpClient())
            .baseUrl(baseUrl.toHttpUrl())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(T::class.java)
    }


    @Volatile
    var weatherRetrofitInstance: WeatherAPI = buildRetrofit(BASE_URL)

    @Synchronized
    fun weatherApi(): WeatherAPI {
        return kotlin.run { weatherRetrofitInstance }
    }
}