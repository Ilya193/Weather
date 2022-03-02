package com.xlwe.weather.domain.errors

import com.xlwe.weather.R
import com.xlwe.weather.domain.resources.ResourceManager
import javax.inject.Inject

class NoConnection @Inject constructor(
    private val resourcesManager: ResourceManager
) : Error {
    override fun getMessage(): String {
        return resourcesManager.getString(R.string.noConnection)
    }
}