package com.xlwe.weather.domain.repositories

import com.xlwe.weather.domain.callbacks.ResultCallback

interface WeatherRepository {

    fun getWeather(city: String, resultCallback: ResultCallback)

}