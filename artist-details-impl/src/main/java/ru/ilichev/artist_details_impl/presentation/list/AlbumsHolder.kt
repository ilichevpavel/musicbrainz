package ru.ilichev.artist_details_impl.presentation.list

import androidx.recyclerview.widget.RecyclerView
import ru.ilichev.artist_details_impl.databinding.ItemAlbumBinding
import ru.ilichev.artist_details_impl.presentation.list.item.AlbumListItem

internal class AlbumsHolder(
    private val binding: ItemAlbumBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: AlbumListItem) {
        binding.tvAlbumTitle.text = item.title
        binding.tvAlbumReleaseDate.text = item.releaseDate
    }
}