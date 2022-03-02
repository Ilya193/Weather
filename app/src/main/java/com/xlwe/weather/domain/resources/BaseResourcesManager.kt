package com.xlwe.weather.domain.resources

import android.content.Context
import javax.inject.Inject

class BaseResourcesManager @Inject constructor(
    private val context: Context
) : ResourceManager {

    override fun getString(stringResIt: Int): String {
        return context.getString(stringResIt)
    }

}