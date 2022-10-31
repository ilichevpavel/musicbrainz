package ru.ilichev.artist_details_impl

import android.content.Context
import android.content.Intent
import ru.ilichev.artist_details_api.ArtistDetailsScreenProvider
import ru.ilichev.artist_details_impl.presentation.ArtistDetailsActivity
import javax.inject.Inject

class ArtistDetailsScreenProviderImpl @Inject constructor() : ArtistDetailsScreenProvider {

    override fun getIntent(context: Context, id: String): Intent {
        return ArtistDetailsActivity.createIntent(context, id)
    }
}