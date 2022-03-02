package com.xlwe.weather.domain.resources

import androidx.annotation.StringRes

interface ResourceManager {

    fun getString(@StringRes stringResIt: Int): String

}