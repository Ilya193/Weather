package com.xlwe.weather.domain.callbacks

import com.xlwe.weather.domain.errors.Error
import com.xlwe.weather.domain.model.Weather

interface ResultCallback {

    fun provideSuccess(data: Weather)

    fun provideError(error: Error)

}