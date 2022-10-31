package ru.ilichev.common.di.component

import android.content.Context
import ru.ilichev.common.ResourceProvider

interface CommonApi {
    fun getContext(): Context
    fun getResourceProvider(): ResourceProvider
}