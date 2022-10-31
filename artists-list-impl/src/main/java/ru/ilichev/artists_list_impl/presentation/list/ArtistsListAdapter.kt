package ru.ilichev.artists_list_impl.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import ru.ilichev.artists_list_impl.databinding.ItemArtistBinding
import ru.ilichev.artists_list_impl.presentation.list.item.ArtistsListItem

internal class ArtistsListAdapter(
    private val onItemClicked: (String) -> Unit,
) : PagingDataAdapter<ArtistsListItem, ArtistItemHolder>(ArtistsListItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistItemHolder {
        val binding = ItemArtistBinding.inflate(LayoutInflater.from(parent.context))
        return ArtistItemHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: ArtistItemHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }
}