package com.xlwe.weather.domain.usecases

import com.xlwe.weather.domain.callbacks.ResultCallback
import com.xlwe.weather.domain.repositories.WeatherRepository

class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {

    fun getWeather(city: String, resultCallback: ResultCallback) {
        weatherRepository.getWeather(city, resultCallback)
    }

}