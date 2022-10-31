package ru.ilichev.artist_details_impl.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.*
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import ru.ilichev.artist_details_impl.data.ArtistDetailsRepository
import ru.ilichev.artist_details_impl.data.ArtistDetailsServiceApi
import ru.ilichev.artist_details_impl.domain.GetArtistDetailsWithAlbumsUseCase
import ru.ilichev.artist_details_impl.presentation.ArtistDetailsActivity
import ru.ilichev.artist_details_impl.presentation.ArtistDetailsViewModel
import ru.ilichev.common.di.BaseInjector
import ru.ilichev.common.di.FeatureScope
import ru.ilichev.common.di.component.CommonApi
import ru.ilichev.common.di.viewmodel.ViewModelFactory
import ru.ilichev.common.di.viewmodel.ViewModelKey
import ru.ilichev.common_network.di.NetworkApi

@Component(
    modules = [ArtistDetailsModule::class],
    dependencies = [NetworkApi::class, CommonApi::class]
)
@FeatureScope
internal interface ArtistDetailsComponent : BaseInjector<ArtistDetailsActivity> {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance id: String,
            commonApi: CommonApi,
            networkApi: NetworkApi
        ): ArtistDetailsComponent
    }
}

@Module
internal abstract class ArtistDetailsModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ArtistDetailsViewModel::class)
    internal abstract fun postListViewModel(viewModel: ArtistDetailsViewModel): ViewModel

    companion object {
        @Provides
        fun provideArtistDetailsApi(retrofit: Retrofit): ArtistDetailsServiceApi =
            retrofit.create(ArtistDetailsServiceApi::class.java)

        @Provides
        fun provideGetArtistDetailsWithAlbumsUseCase(
            artistDetailsRepository: ArtistDetailsRepository
        ): GetArtistDetailsWithAlbumsUseCase {
            return GetArtistDetailsWithAlbumsUseCase(artistDetailsRepository)
        }
    }
}
