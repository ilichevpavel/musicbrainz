package ru.ilichev.artists_list_impl.presentation.list

import androidx.recyclerview.widget.DiffUtil
import ru.ilichev.artists_list_impl.presentation.list.item.ArtistsListItem

internal class ArtistsListItemCallback : DiffUtil.ItemCallback<ArtistsListItem>() {

    override fun areItemsTheSame(oldItem: ArtistsListItem, newItem: ArtistsListItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ArtistsListItem, newItem: ArtistsListItem): Boolean {
        return oldItem == newItem
    }
}