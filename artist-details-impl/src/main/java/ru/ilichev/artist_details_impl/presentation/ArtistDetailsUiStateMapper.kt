package ru.ilichev.artist_details_impl.presentation

import ru.ilichev.artist_details_impl.R
import ru.ilichev.artist_details_impl.domain.ArtistDetails
import ru.ilichev.artist_details_impl.presentation.list.item.AlbumListItem
import ru.ilichev.common.ResourceProvider
import javax.inject.Inject

internal class ArtistDetailsUiStateMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun getContentState(model: ArtistDetails): ArtistDetailsUiState {
        return ArtistDetailsUiState.Content(
            title = model.name,
            albums = model.releases.map {
                AlbumListItem(
                    id = it.id,
                    title = it.title,
                    releaseDate = resourceProvider.getString(
                        R.string.release_date_title,
                        it.releaseDate
                    )
                )
            }
        )
    }
}