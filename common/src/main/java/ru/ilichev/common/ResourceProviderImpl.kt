package ru.ilichev.common

import android.content.Context
import javax.inject.Inject

internal class ResourceProviderImpl @Inject constructor(
    private val context: Context
) : ResourceProvider {

    override fun getString(id: Int, vararg args: Any?): String {
        return context.resources.getString(id, *args)
    }
}