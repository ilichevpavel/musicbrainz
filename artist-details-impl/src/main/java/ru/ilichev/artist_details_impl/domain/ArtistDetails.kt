package ru.ilichev.artist_details_impl.domain

internal data class ArtistDetails(
    val name: String,
    val releases: List<Release>
)

internal data class Release(
    val id: String,
    val title: String,
    val type: ReleaseType,
    val releaseDate: String
)

internal enum class ReleaseType {
    ALBUM,
    UNKNOWN;

    companion object {
        fun valueOfIgnoreCase(value: String?): ReleaseType {
            if (value == null) return UNKNOWN

            values().forEach {
                if (it.name.compareTo(value, ignoreCase = true) == 0) {
                    return it
                }
            }

            return UNKNOWN
        }
    }
}