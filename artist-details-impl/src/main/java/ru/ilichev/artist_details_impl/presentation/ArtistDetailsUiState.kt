package ru.ilichev.artist_details_impl.presentation

import ru.ilichev.artist_details_impl.presentation.list.item.AlbumListItem

internal sealed interface ArtistDetailsUiState {
    object Loading : ArtistDetailsUiState
    object Error : ArtistDetailsUiState
    data class Content(
        val title: String,
        val albums: List<AlbumListItem>
    ) : ArtistDetailsUiState
}