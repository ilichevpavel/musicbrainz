package ru.ilichev.common_network.di

import retrofit2.Retrofit

interface NetworkApi {
    fun getRetrofit(): Retrofit
}