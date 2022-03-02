package com.xlwe.weather.data.network.model

data class WeatherTDO(
    val description: String,
    val forecast: List<ForecastTDO>,
    val temperature: String,
    val wind: String
)