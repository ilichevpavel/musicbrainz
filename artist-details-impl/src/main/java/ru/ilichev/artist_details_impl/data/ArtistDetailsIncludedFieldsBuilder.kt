package ru.ilichev.artist_details_impl.data

private const val FIELDS_SEPARATOR = "+"

internal class ArtistDetailsIncludedFieldsBuilder {

    private val fields = mutableSetOf<Field>()

    fun addReleaseGroups(): ArtistDetailsIncludedFieldsBuilder {
        fields.add(Field.RELEASE_GROUPS)
        return this
    }

    fun build(): String {
        return fields.joinToString(FIELDS_SEPARATOR) { it.value }
    }

    private enum class Field(val value: String) {
        RELEASE_GROUPS("release-groups")
    }
}