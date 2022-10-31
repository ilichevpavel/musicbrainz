package ru.ilichev.common.di.component

import android.content.Context
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import ru.ilichev.common.ResourceProvider
import ru.ilichev.common.ResourceProviderImpl

@Component(
    modules = [CommonModule::class]
)
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
    internal abstract fun bindResourceProvider(impl: ResourceProviderImpl): ResourceProvider
}