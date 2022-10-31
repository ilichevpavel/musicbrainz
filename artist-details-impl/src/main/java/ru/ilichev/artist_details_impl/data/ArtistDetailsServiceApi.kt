package ru.ilichev.artist_details_impl.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.ilichev.artist_details_impl.data.model.ArtistDetailsResponse

internal interface ArtistDetailsServiceApi {

    @GET("artist/{id}")
    suspend fun getArtistDetails(
        @Path("id") id: String,
        @Query("inc") includedFields: String
    ): ArtistDetailsResponse
}