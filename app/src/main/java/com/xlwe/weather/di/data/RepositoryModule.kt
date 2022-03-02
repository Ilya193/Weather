package com.xlwe.weather.di.data

import com.xlwe.weather.data.network.ApiRequests
import com.xlwe.weather.data.repositories.WeatherRepositoryImpl
import com.xlwe.weather.domain.errors.NoConnection
import com.xlwe.weather.domain.errors.ServiceUnavailable
import com.xlwe.weather.domain.repositories.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideWeatherRepository(
        apiRequests: ApiRequests,
        noConnection: NoConnection,
        serviceUnavailable: ServiceUnavailable
    ): WeatherRepository {
        return WeatherRepositoryImpl(apiRequests, noConnection, serviceUnavailable)
    }

}