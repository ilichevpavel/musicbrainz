package ru.ilichev.artist_details_impl.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.ilichev.artist_details_impl.data.ArtistDetailsRepository

internal class GetArtistDetailsWithAlbumsUseCase(
    private val artistDetailsRepository: ArtistDetailsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : suspend (String) -> Result<ArtistDetails> {

    override suspend fun invoke(id: String): Result<ArtistDetails> = withContext(dispatcher) {
        val artistDetails = artistDetailsRepository.getArtistDetailsWithReleases(id)

        artistDetails.map {
            it.copy(
                releases = it.releases.filter { it.type == ReleaseType.ALBUM }
            )
        }
    }
}