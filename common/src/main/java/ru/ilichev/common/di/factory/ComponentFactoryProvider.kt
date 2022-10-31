package ru.ilichev.common.di.factory

interface ComponentFactoryProvider {

    fun getComponentProvider(): ComponentFactory
}