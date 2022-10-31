package ru.ilichev.common

import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes id: Int, vararg args: Any?): String
}