package ru.ilichev.artists_list_impl.data

import retrofit2.http.GET
import retrofit2.http.Query
import ru.ilichev.artists_list_impl.data.model.ArtistListResponse

internal interface ArtistsServiceApi {

    @GET("artist")
    suspend fun getArtists(
        @Query("query") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ArtistListResponse
}