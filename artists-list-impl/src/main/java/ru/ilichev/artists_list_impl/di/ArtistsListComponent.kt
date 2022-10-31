package ru.ilichev.artists_list_impl.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import ru.ilichev.artist_details_api.ArtistDetailsApi
import ru.ilichev.artists_list_impl.data.ArtistsServiceApi
import ru.ilichev.artists_list_impl.presentation.ArtistsListActivity
import ru.ilichev.artists_list_impl.presentation.ArtistsListViewModel
import ru.ilichev.common.di.BaseInjector
import ru.ilichev.common.di.FeatureScope
import ru.ilichev.common.di.viewmodel.ViewModelFactory
import ru.ilichev.common.di.viewmodel.ViewModelKey
import ru.ilichev.common_network.di.NetworkApi

@Component(
    modules = [ArtistsListModule::class],
    dependencies = [NetworkApi::class, ArtistDetailsApi::class]
)
@FeatureScope
internal interface ArtistsListComponent : BaseInjector<ArtistsListActivity> {

    @Component.Factory
    interface Factory {
        fun create(
            networkApi: NetworkApi,
            artistDetailsApi: ArtistDetailsApi
        ): ArtistsListComponent
    }
}

@Module
internal abstract class ArtistsListModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ArtistsListViewModel::class)
    internal abstract fun postListViewModel(viewModel: ArtistsListViewModel): ViewModel

    companion object {
        @Provides
        internal fun provideArtistsServiceApi(retrofit: Retrofit): ArtistsServiceApi =
            retrofit.create(ArtistsServiceApi::class.java)
    }
}