package ru.ilichev.artists_list_impl.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.ilichev.artists_list_impl.data.model.ArtistResponse

internal class ArtistsListPagingSource(
    private val query: String,
    private val serviceApi: ArtistsServiceApi
) : PagingSource<Int, ArtistResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtistResponse> {
        val currentOffset = params.key ?: 0

        return runCatching {
            val response = serviceApi.getArtists(
                query = query,
                limit = params.loadSize,
                offset = currentOffset
            )
            val artists = response.artists

            val nextKey = (currentOffset + artists.count()).takeIf { it < response.count }

            LoadResult.Page(
                data = artists,
                prevKey = null,
                nextKey = nextKey
            )
        }.getOrElse { error -> LoadResult.Error(error) }
    }

    override fun getRefreshKey(state: PagingState<Int, ArtistResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}