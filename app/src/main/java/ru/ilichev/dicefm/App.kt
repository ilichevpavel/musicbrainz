package ru.ilichev.dicefm

import android.app.Application
import ru.ilichev.common.di.factory.ComponentFactoryProvider
import ru.ilichev.common.di.factory.ComponentFactory

class App : Application(), ComponentFactoryProvider {

    private val componentFactory by lazy { ComponentFactory(this) }

    override fun getComponentProvider(): ComponentFactory {
        return componentFactory
    }
}