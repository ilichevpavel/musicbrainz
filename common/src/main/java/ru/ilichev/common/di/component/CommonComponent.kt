package ru.ilichev.common.di.component

import android.content.Context
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import ru.ilichev.common.ResourceProvider
import ru.ilichev.common.ResourceProviderImpl
import javax.inject.Singleton

@Component(
    modules = [CommonModule::class]
)
@Singleton
interface CommonComponent : CommonApi {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindContext(context: Context): Builder
        fun build(): CommonComponent
    }
}

@Module
internal abstract class CommonModule {

    @Binds
    @Singleton
    internal abstract fun bindResourceProvider(impl: ResourceProviderImpl): ResourceProvider
}