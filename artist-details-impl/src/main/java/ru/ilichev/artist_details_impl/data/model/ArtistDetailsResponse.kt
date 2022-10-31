@file:OptIn(ExperimentalSerializationApi::class)

package ru.ilichev.artist_details_impl.data.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
internal class ArtistDetailsResponse(
    val name: String,
    @JsonNames("release-groups") val releases: List<ArtistRelease>
)

@Serializable
internal class ArtistRelease(
    val id: String,
    val title: String,
    @JsonNames("primary-type") val type: String? = null,
    @JsonNames("first-release-date") val releaseDate: String? = null
)
