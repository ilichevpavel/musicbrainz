package ru.ilichev.common_network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

private const val BASE_URL = "https://musicbrainz.org/ws/2/"
private const val CONTENT_TYPE = "application/json"

@OptIn(ExperimentalSerializationApi::class)
internal class RetrofitFactory(
    private val json: Json
) {

    fun getDefault(): Retrofit {
        val okhttp = OkHttpClient.Builder()
            .addInterceptor(ContentTypeInterceptor())
            .addInterceptor(UserAgentInterceptor(getUserAgentValue()))
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okhttp)
            .addConverterFactory(json.asConverterFactory(CONTENT_TYPE.toMediaType()))
            .build()
    }

    private fun getUserAgentValue(): String {
        return UserAgent.value
    }
}