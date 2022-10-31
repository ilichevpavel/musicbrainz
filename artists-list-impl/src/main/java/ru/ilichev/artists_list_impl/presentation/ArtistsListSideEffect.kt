package ru.ilichev.artists_list_impl.presentation

sealed interface ArtistsListSideEffect {
    class OpenArtistDetails(val id: String) : ArtistsListSideEffect
}