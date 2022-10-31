package ru.ilichev.artists_list_impl.data.model

import kotlinx.serialization.Serializable

@Serializable
class ArtistListResponse(
    val count: Int,
    val artists: List<ArtistResponse>
)

@Serializable
class ArtistResponse(
    val id: String,
    val name: String
)