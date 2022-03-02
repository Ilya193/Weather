package com.xlwe.weather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xlwe.weather.domain.callbacks.ResultCallback
import com.xlwe.weather.domain.errors.EnterError
import com.xlwe.weather.domain.errors.Error
import com.xlwe.weather.domain.model.Weather
import com.xlwe.weather.domain.usecases.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val enterError: EnterError
) : ViewModel() {

    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather>
        get() = _weather

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun getWeather(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getWeatherUseCase.getWeather(city, object : ResultCallback {
                override fun provideSuccess(data: Weather) {
                    if (data.description == "" || data.wind == "" || data.temperature == "") {
                        _error.postValue(enterError.getMessage())
                    } else {
                        var description = ""

                        if (data.description == "Partly cloudy")
                            description = "Переменная облачность"
                        if (data.description == "Sunny")
                            description = "Солнечно"
                        if (data.description == "Clear")
                            description = "Ясно"

                        val wind =
                            (data.wind.replace("km/h", "", false).toDouble() * 1000 / 3600).toInt()
                                .toString() + " м/с"

                        val weather = Weather(
                            description = description,
                            forecast = data.forecast,
                            temperature = data.temperature,
                            wind = wind
                        )

                        _weather.postValue(weather)
                    }
                }

                override fun provideError(error: Error) {
                    _error.postValue(error.getMessage())
                }
            })
        }
    }

}