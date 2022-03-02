package com.xlwe.weather.di.domain

import android.content.Context
import com.xlwe.weather.domain.resources.BaseResourcesManager
import com.xlwe.weather.domain.resources.ResourceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class ResourceManagerModule {

    @Provides
    fun provideResourceManager(@ApplicationContext context: Context): ResourceManager {
        return BaseResourcesManager(context)
    }

}