package ru.ilichev.common_network

import okhttp3.Interceptor
import okhttp3.Response

private const val HEADER_USER_AGENT = "User-Agent"

internal class UserAgentInterceptor(private val userAgentValue: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(HEADER_USER_AGENT, userAgentValue)
            .build()
        return chain.proceed(request)
    }
}