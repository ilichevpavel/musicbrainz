package ru.ilichev.common_network

import okhttp3.Interceptor
import okhttp3.Response

private const val CONTENT_TYPE_QUERY_PARAM = "fmt"

internal class ContentTypeInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val urlWithQuery = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(CONTENT_TYPE_QUERY_PARAM, "json")
            .build()

        val request = chain.request()
            .newBuilder()
            .url(urlWithQuery)
            .build()

        return chain.proceed(request)
    }
}