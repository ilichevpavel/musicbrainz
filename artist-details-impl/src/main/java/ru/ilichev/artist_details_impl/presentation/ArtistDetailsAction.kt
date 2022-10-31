package ru.ilichev.artist_details_impl.presentation

internal sealed interface ArtistDetailsAction {

    object OnRetryClicked : ArtistDetailsAction
}