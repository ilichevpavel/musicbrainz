package ru.ilichev.common.di.factory

import android.app.Activity

fun Activity.getComponentFactory(): ComponentFactory {
    return (application as ComponentFactoryProvider).getComponentProvider()
}