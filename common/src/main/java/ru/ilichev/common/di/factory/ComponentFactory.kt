package ru.ilichev.common.di.factory

import kotlin.reflect.KClass

interface ComponentFactory {

    operator fun <T : Any> get(kClass: KClass<T>): T
}