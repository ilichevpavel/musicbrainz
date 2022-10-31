package ru.ilichev.artists_list_impl.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.ilichev.artists_list_impl.data.model.ArtistResponse
import ru.ilichev.artists_list_impl.domain.Artist
import javax.inject.Inject

internal class ArtistsRepository @Inject constructor(
    private val serviceApi: ArtistsServiceApi
) {

    fun getArtists(query: String): Flow<PagingData<Artist>> {
        return Pager(
            config = PagingConfig(prefetchDistance = 5, pageSize = 20),
            pagingSourceFactory = { ArtistsListPagingSource(query, serviceApi) }
        )
            .flow
            .map { it.map(::mapArtists) }
    }

    private fun mapArtists(response: ArtistResponse): Artist = with(response) {
        Artist(id = id, name = name)
    }
}