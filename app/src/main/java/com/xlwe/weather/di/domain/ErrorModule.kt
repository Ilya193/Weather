package com.xlwe.weather.di.domain

import com.xlwe.weather.domain.errors.EnterError
import com.xlwe.weather.domain.errors.NoConnection
import com.xlwe.weather.domain.errors.ServiceUnavailable
import com.xlwe.weather.domain.resources.ResourceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ErrorModule {

    @Provides
    fun provideNoConnection(resourceManager: ResourceManager): NoConnection {
        return NoConnection(resourceManager)
    }

    @Provides
    fun provideServiceUnavailable(resourceManager: ResourceManager): ServiceUnavailable {
        return ServiceUnavailable(resourceManager)
    }

    @Provides
    fun provideEnterError(resourceManager: ResourceManager): EnterError {
        return EnterError(resourceManager)
    }

}