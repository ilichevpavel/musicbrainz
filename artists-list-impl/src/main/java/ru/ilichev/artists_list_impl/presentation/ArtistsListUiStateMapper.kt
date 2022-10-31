package ru.ilichev.artists_list_impl.presentation

import androidx.paging.PagingData
import androidx.paging.map
import ru.ilichev.artists_list_impl.domain.Artist
import ru.ilichev.artists_list_impl.presentation.list.item.ArtistsListItem
import javax.inject.Inject

internal class ArtistsListUiStateMapper @Inject constructor() {

    fun mapPagingData(pagingData: PagingData<Artist>): ArtistsListUiState {
        return ArtistsListUiState.Content(pagingData.map { ArtistsListItem(it.id, it.name) })
    }
}