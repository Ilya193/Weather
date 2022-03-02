package com.xlwe.weather.domain.errors

import com.xlwe.weather.R
import com.xlwe.weather.domain.resources.ResourceManager
import javax.inject.Inject

class ServiceUnavailable @Inject constructor(
    private val resourceManager: ResourceManager
) : Error {
    override fun getMessage(): String {
        return resourceManager.getString(R.string.serviceUnavailable)
    }
}