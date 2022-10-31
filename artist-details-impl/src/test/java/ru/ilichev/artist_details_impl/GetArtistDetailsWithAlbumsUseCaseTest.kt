package ru.ilichev.artist_details_impl

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import ru.ilichev.artist_details_impl.data.ArtistDetailsRepository
import ru.ilichev.artist_details_impl.domain.ArtistDetails
import ru.ilichev.artist_details_impl.domain.GetArtistDetailsWithAlbumsUseCase
import ru.ilichev.artist_details_impl.domain.Release
import ru.ilichev.artist_details_impl.domain.ReleaseType

@OptIn(ExperimentalCoroutinesApi::class)
class GetArtistDetailsWithAlbumsUseCaseTest {

    private val repository = mockk<ArtistDetailsRepository>()
    private val getArtistDetailsWithAlbumsUseCase = GetArtistDetailsWithAlbumsUseCase(repository)

    @Test
    fun `get albums from only album releases`() = runTest {
        val artistsSource = Result.success(
            ArtistDetails(
                name = "name",
                releases = listOf(
                    Release(
                        id = "id1",
                        title = "title1",
                        type = ReleaseType.ALBUM,
                        releaseDate = "01.01.2022"
                    ),
                    Release(
                        id = "id3",
                        title = "title1",
                        type = ReleaseType.ALBUM,
                        releaseDate = "01.01.2022"
                    ),
                    Release(
                        id = "id3",
                        title = "title1",
                        type = ReleaseType.ALBUM,
                        releaseDate = "01.01.2022"
                    )
                )
            )
        )
        coEvery { repository.getArtistDetailsWithReleases(any()) } returns artistsSource

        val actual = getArtistDetailsWithAlbumsUseCase("id")

        assert(actual.getOrThrow() == artistsSource.getOrThrow())
    }

    @Test
    fun `get albums from different release types`() = runTest {
        val artistsSource = Result.success(
            ArtistDetails(
                name = "name",
                releases = listOf(
                    Release(
                        id = "id1",
                        title = "title1",
                        type = ReleaseType.ALBUM,
                        releaseDate = "01.01.2022"
                    ),
                    Release(
                        id = "id3",
                        title = "title1",
                        type = ReleaseType.UNKNOWN,
                        releaseDate = "01.01.2022"
                    ),
                    Release(
                        id = "id3",
                        title = "title1",
                        type = ReleaseType.ALBUM,
                        releaseDate = "01.01.2022"
                    )
                )
            )
        )
        coEvery { repository.getArtistDetailsWithReleases(any()) } returns artistsSource

        val actual = getArtistDetailsWithAlbumsUseCase("id")
        val expected = Result.success(
            ArtistDetails(
                name = "name",
                releases = listOf(
                    Release(
                        id = "id1",
                        title = "title1",
                        type = ReleaseType.ALBUM,
                        releaseDate = "01.01.2022"
                    ),
                    Release(
                        id = "id3",
                        title = "title1",
                        type = ReleaseType.ALBUM,
                        releaseDate = "01.01.2022"
                    )
                )
            )
        )

        assert(actual.getOrThrow() == expected.getOrThrow())
    }
}