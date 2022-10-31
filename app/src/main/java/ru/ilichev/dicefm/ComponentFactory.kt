package ru.ilichev.dicefm

import android.content.Context
import ru.ilichev.artist_details_api.ArtistDetailsApi
import ru.ilichev.artist_details_impl.di.DaggerArtistDetailsApiComponent
import ru.ilichev.common.di.component.CommonApi
import ru.ilichev.common.di.component.CommonComponent
import ru.ilichev.common.di.component.DaggerCommonComponent
import ru.ilichev.common.di.factory.ComponentFactory
import ru.ilichev.common_network.di.DaggerNetworkComponent
import ru.ilichev.common_network.di.NetworkApi
import ru.ilichev.common_network.di.NetworkComponent
import kotlin.reflect.KClass

class ComponentFactory(private val context: Context) : ComponentFactory {

    @Volatile
    private var networkComponent: NetworkComponent? = null

    @Volatile
    private var commonComponent: CommonComponent? = null

    private val componentsMap = mapOf<Any, () -> Any>(
        NetworkApi::class to { getNetworkComponent() },
        ArtistDetailsApi::class to { getArtistDetailsComponent() },
        CommonApi::class to { getCommonComponent() }
    )

    override fun <T : Any> get(kClass: KClass<T>): T {
        return componentsMap[kClass]!!.invoke() as T
    }

    private fun getCommonComponent(): CommonComponent {
        return commonComponent ?: synchronized(this) {
            commonComponent ?: DaggerCommonComponent
                .builder()
                .bindContext(context)
                .build()
        }.also {
            commonComponent = it
        }
    }

    private fun getNetworkComponent(): NetworkComponent {
        return networkComponent ?: synchronized(this) {
            networkComponent ?: DaggerNetworkComponent.builder().build()
        }.also {
            networkComponent = it
        }
    }

    private fun getArtistDetailsComponent(): ArtistDetailsApi {
        return DaggerArtistDetailsApiComponent.builder().build()
    }
}