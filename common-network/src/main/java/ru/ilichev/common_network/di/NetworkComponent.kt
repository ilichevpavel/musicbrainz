package ru.ilichev.common_network.di

import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import ru.ilichev.common_network.RetrofitFactory
import javax.inject.Singleton

@Component(
    modules = [NetworkModule::class]
)
@Singleton
interface NetworkComponent : NetworkApi {

    @Component.Builder
    interface Builder {

        fun build(): NetworkComponent
    }
}

@Module
object NetworkModule {

    @Provides
    @Singleton
    internal fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(json: Json): Retrofit =
        RetrofitFactory(json).getDefault()
}