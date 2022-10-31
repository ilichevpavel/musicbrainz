package ru.ilichev.artist_details_impl.data

import ru.ilichev.artist_details_impl.data.model.ArtistDetailsResponse
import ru.ilichev.artist_details_impl.domain.ArtistDetails
import ru.ilichev.artist_details_impl.domain.Release
import ru.ilichev.artist_details_impl.domain.ReleaseType
import javax.inject.Inject

internal class ArtistDetailsRepository @Inject constructor(
    private val serviceApi: ArtistDetailsServiceApi
) {

    suspend fun getArtistDetailsWithReleases(id: String): Result<ArtistDetails> {
        val includedFields = ArtistDetailsIncludedFieldsBuilder()
            .addReleaseGroups()
            .build()

        return runCatching {
            serviceApi.getArtistDetails(
                id = id,
                includedFields = includedFields
            )
        }.mapCatching(::mapModel)
    }

    private fun mapModel(response: ArtistDetailsResponse): ArtistDetails {
        return ArtistDetails(
            name = response.name,
            releases = response.releases.map { releaseResponse ->
                Release(
                    id = releaseResponse.id,
                    title = releaseResponse.title,
                    type = releaseResponse.type.let(ReleaseType::valueOfIgnoreCase),
                    releaseDate = releaseResponse.releaseDate.orEmpty()
                )
            }
        )
    }
}