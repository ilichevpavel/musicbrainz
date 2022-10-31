package ru.ilichev.common.di

interface BaseInjector<T> {

    fun inject(injected: T)
}