package ru.ilichev.artists_list_impl.presentation

import androidx.paging.LoadState

sealed interface ArtistsListAction {
    class OnSearchChanged(val query: String) : ArtistsListAction
    class OnArtistClicked(val id: String) : ArtistsListAction
    class OnRefreshLoadStateChanged(
        val loadState: LoadState,
        val itemsCount: Int
    ) : ArtistsListAction
}