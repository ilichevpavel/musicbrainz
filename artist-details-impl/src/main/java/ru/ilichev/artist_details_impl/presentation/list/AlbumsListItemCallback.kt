package ru.ilichev.artist_details_impl.presentation.list

import androidx.recyclerview.widget.DiffUtil
import ru.ilichev.artist_details_impl.presentation.list.item.AlbumListItem

internal class AlbumsListItemCallback : DiffUtil.ItemCallback<AlbumListItem>() {

    override fun areItemsTheSame(oldItem: AlbumListItem, newItem: AlbumListItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AlbumListItem, newItem: AlbumListItem): Boolean {
        return oldItem == newItem
    }
}