package com.xlwe.weather.data.repositories

import com.xlwe.weather.data.mappers.WeatherMapper
import com.xlwe.weather.data.network.ApiRequests
import com.xlwe.weather.data.network.model.WeatherTDO
import com.xlwe.weather.domain.callbacks.ResultCallback
import com.xlwe.weather.domain.errors.NoConnection
import com.xlwe.weather.domain.errors.ServiceUnavailable
import com.xlwe.weather.domain.repositories.WeatherRepository
import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiRequests: ApiRequests,
    private val noConnection: NoConnection,
    private val serviceUnavailable: ServiceUnavailable
) : WeatherRepository {

    private val mapper = WeatherMapper()

    override fun getWeather(city: String, resultCallback: ResultCallback) {
        apiRequests.getWeather(city).enqueue(object : retrofit2.Callback<WeatherTDO>{
            override fun onResponse(call: Call<WeatherTDO>, response: Response<WeatherTDO>) {
                if (response.body() == null) {
                    resultCallback.provideError(serviceUnavailable)
                }
                else {
                    resultCallback.provideSuccess(mapper.mapNetworkModelToEntity(response.body()!!))
                }
            }

            override fun onFailure(call: Call<WeatherTDO>, t: Throwable) {
                if (t is UnknownHostException) {
                    resultCallback.provideError(noConnection)
                }
                else {
                    resultCallback.provideError(serviceUnavailable)
                }
            }
        })
    }

}