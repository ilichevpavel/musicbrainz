package ru.ilichev.artists_list_impl.presentation

import androidx.paging.PagingData
import ru.ilichev.artists_list_impl.presentation.list.item.ArtistsListItem

internal sealed interface ArtistsListUiState {
    object Error : ArtistsListUiState
    object Empty : ArtistsListUiState
    object FirstPagingLoading : ArtistsListUiState
    data class Content(
        val items: PagingData<ArtistsListItem> = PagingData.empty()
    ) : ArtistsListUiState
}