package ru.ilichev.artist_details_api

import android.content.Context
import android.content.Intent

interface ArtistDetailsScreenProvider {

    fun getIntent(context: Context, id: String): Intent
}