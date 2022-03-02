package com.xlwe.weather.data.network

import com.xlwe.weather.data.network.model.WeatherTDO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRequests {
    @GET("weather/{city}")
    fun getWeather(@Path("city") city: String): Call<WeatherTDO>
}