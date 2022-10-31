package ru.ilichev.artist_details_impl

import org.junit.Test
import ru.ilichev.artist_details_impl.data.ArtistDetailsIncludedFieldsBuilder

class ArtistDetailsIncludedFieldsBuilderTest {

    private val builder = ArtistDetailsIncludedFieldsBuilder()

    @Test
    fun `create fields with release groups`() {
        val actual = builder.addReleaseGroups().build()
        val expected = "release-groups"

        assert(actual == expected)
    }

    @Test
    fun `create fields with release groups several times`() {
        val actual = builder.addReleaseGroups().addReleaseGroups().build()
        val expected = "release-groups"

        assert(actual == expected)
    }
}