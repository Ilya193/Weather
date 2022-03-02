package com.xlwe.weather.data.mappers

import com.xlwe.weather.data.network.model.WeatherTDO
import com.xlwe.weather.domain.model.Forecast
import com.xlwe.weather.domain.model.Weather

class WeatherMapper {

    fun mapNetworkModelToEntity(weatherTDO: WeatherTDO): Weather {
        val networkForecast = weatherTDO.forecast
        val entityForecast = mutableListOf<Forecast>()

        networkForecast.forEach {
            val forecast = Forecast(
                it.day,
                it.temperature,
                it.wind
            )
            entityForecast.add(forecast)
        }

        return Weather(
            description = weatherTDO.description,
            forecast = entityForecast,
            temperature = weatherTDO.temperature,
            wind = weatherTDO.wind
        )
    }

}