package ru.ilichev.artist_details_impl.di

import dagger.Binds
import dagger.Component
import dagger.Module
import ru.ilichev.artist_details_api.ArtistDetailsApi
import ru.ilichev.artist_details_api.ArtistDetailsScreenProvider
import ru.ilichev.artist_details_impl.ArtistDetailsScreenProviderImpl

@Component(modules = [ArtistDetailsApiModule::class])
interface ArtistDetailsApiComponent : ArtistDetailsApi {

    @Component.Builder
    interface Builder {
        fun build(): ArtistDetailsApiComponent
    }
}

@Module
internal abstract class ArtistDetailsApiModule {

    @Binds
    abstract fun bindArtistDetailsScreenProvider(
        impl: ArtistDetailsScreenProviderImpl
    ): ArtistDetailsScreenProvider
}
